package project.ticket.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<OrderDto> search(OrderSearchForm orderSearchForm, Pageable pageable) {
        QueryResults<OrderDto> results = queryFactory
                // DTO를 통해서 반환하는 이유는 단순 데이터만 전달하기 위해
                // 엔티티를 그대로 사용하면 엔티티 변경시 전체적인 구조와 변경사항이 많아지기 때문에
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
                // where 검색조건을 메서드로 작성
                // 관리하기 편하고 추가할때도 편함
                .where(
                        memberNameEq(orderSearchForm.getMemberName()),
                        orderStatusEq(orderSearchForm.getOrderStatus())
                )
                // 주문 최신순으로 정렬
                .orderBy(order.createdDate.desc())
                // 반환 타입이 Page
                // paging처리에 대한 편리함 함수들 제공
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<OrderDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);
    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatus) {
        return orderStatus != null ? order.status.eq(orderStatus) : null;
    }

    private BooleanExpression memberNameEq(String memberName) {
        return StringUtils.hasText(memberName) ? member.username.eq(memberName) : null;
    }
}
