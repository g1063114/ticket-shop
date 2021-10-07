package project.ticket.shop.entity.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.dto.OrderDto;
import project.ticket.shop.dto.OrderSearchForm;
import project.ticket.shop.entity.Member;
import project.ticket.shop.entity.Order;
import project.ticket.shop.entity.OrderItem;
import project.ticket.shop.entity.OrderStatus;
import project.ticket.shop.entity.item.Movie;
import project.ticket.shop.repository.OrderRepository;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderTest {
    @Autowired
    EntityManager em;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void saveOrder(){
        Member member1 = new Member("member1", "g1063114@naver.com", 28);

        Movie movie = new Movie();
        movie.setName("샹치와 텐 링즈의 전설");
        movie.setPrice(13000);
        movie.setStock(160);
        movie.setGenre("액션");
        movie.setRunningTime(132);

        em.persist(member1);
        em.persist(movie);

        em.flush();
        em.clear();

        OrderItem orderItem = OrderItem.saveOrderItem(movie, 3, 13000);

        Order order = Order.saveOrder(member1,orderItem);

        orderRepository.save(order);

        Optional<Order> findOrder = orderRepository.findById(order.getId());

        assertThat(findOrder.get().getMember().getUsername()).isEqualTo("member1");
    }

    @Test
    public void orderSearch(){
        // 기본 회원 생성
        Member member1 = new Member("member1", "g1063114@naver.com", 28);

        // 상품 생성
        Movie movie = new Movie();
        movie.setName("샹치와 텐 링즈의 전설");
        movie.setPrice(13000);
        movie.setStock(160);
        movie.setGenre("액션");
        movie.setRunningTime(132);

        // 영속성 컨텍스트에 영속
        em.persist(member1);
        em.persist(movie);

        // 주문 생성 과정
        OrderItem orderItem = OrderItem.saveOrderItem(movie, 3, 13000);
        Order order = Order.saveOrder(member1,orderItem);
        orderRepository.save(order);

        // 검색 조건을 설정해서 동적쿼리 적용하게 함
        OrderSearchForm orderSearchForm = new OrderSearchForm();
        orderSearchForm.setOrderStatus(OrderStatus.ORDER);
        orderSearchForm.setMemberName("member1");

        // 페이지 파라미터 설정
        PageRequest pageRequest = PageRequest.of(0,3);
        // 동적쿼리 적용한 search에서 DTO로 반환
        Page<OrderDto> findOrder = orderRepository.search(orderSearchForm, pageRequest);

        // 검증
        assertThat(findOrder.getSize()).isEqualTo(3);
        assertThat(findOrder.getContent()).extracting("memberName").containsExactly("member1");
        System.out.println("findOrder = " + findOrder.getContent());
        System.out.println("findOrder = " + findOrder.hasNext());
        System.out.println("findOrder = " + findOrder.hasPrevious());
        System.out.println("findOrder = " + findOrder.getTotalPages());
        System.out.println("findOrder = " + findOrder.getTotalElements());
    }
}
