package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.dto.OrderDto;
import project.ticket.shop.dto.OrderSearchForm;
import project.ticket.shop.entity.Member;
import project.ticket.shop.entity.Order;
import project.ticket.shop.entity.OrderItem;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.entity.item.Snack;
import project.ticket.shop.repository.ItemRepository;
import project.ticket.shop.repository.MemberRepository;
import project.ticket.shop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
     * 상품 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member findMember = memberRepository.findById(memberId).get();
        Item findItem = itemRepository.findById(itemId).get();

        OrderItem orderItem = OrderItem.saveOrderItem(findItem, count, findItem.getPrice());

        Order order = Order.saveOrder(findMember,orderItem);
        
        orderRepository.save(order);
        return order.getId();
    }

    /*
     * 상품 출력
     */
    public Page<OrderDto> orderList(OrderSearchForm orderSearchForm, Pageable pageable){
        Page<OrderDto> orderList = orderRepository.search(orderSearchForm, pageable);
        return orderList;
    }

    /*
     * 상품 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        Order findOrder = orderRepository.findById(orderId).get();
        findOrder.cancel();
    }

}
