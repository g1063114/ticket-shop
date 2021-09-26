package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.Member;
import project.ticket.shop.entity.Order;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.repository.ItemRepository;
import project.ticket.shop.repository.MemberRepository;
import project.ticket.shop.repository.OrderRepository;

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
    public void order(Long memberId, Long itemId, int count){
        Member findMember = memberRepository.findById(memberId).get();
        Item findItem = itemRepository.findById(itemId).get();

        Order order = new Order();
    }
}
