package project.ticket.shop.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import project.ticket.shop.dto.OrderDto;
import project.ticket.shop.dto.OrderSearchForm;
import project.ticket.shop.dto.QOrderDto;
import project.ticket.shop.entity.*;
import project.ticket.shop.entity.item.QItem;

import javax.persistence.EntityManager;
import java.util.List;

import static project.ticket.shop.entity.QMember.member;
import static project.ticket.shop.entity.QOrder.order;
import static project.ticket.shop.entity.QOrderItem.orderItem;
import static project.ticket.shop.entity.item.QItem.item;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderDto> search(OrderSearchForm orderSearchForm) {
        List<OrderDto> result = queryFactory
                .select(
                        new QOrderDto(
                                order.id,
                                member.username,
                                item.name,
                                orderItem.orderPrice,
                                orderItem.count,
                                order.status,
                                order.createdDate
                        )
                )
                .from(order)
                .leftJoin(order.member, member)
                .leftJoin(order.orderItems, orderItem)
                .leftJoin(orderItem.item, item)
                .where(
                        memberNameEq(orderSearchForm.getMemberName()),
                        orderStatusEq(orderSearchForm.getOrderStatus())
                )
                .fetch();

        return result;
    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatus) {
        return orderStatus != null ? order.status.eq(orderStatus) : null;
    }

    private BooleanExpression memberNameEq(String memberName) {
        return StringUtils.hasText(memberName) ? member.username.eq(memberName) : null;
    }
}
