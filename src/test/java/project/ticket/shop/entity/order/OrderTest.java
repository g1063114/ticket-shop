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
        Member member1 = new Member("member1", "g1063114@naver.com", 28);

        Movie movie = new Movie();
        movie.setName("샹치와 텐 링즈의 전설");
        movie.setPrice(13000);
        movie.setStock(160);
        movie.setGenre("액션");
        movie.setRunningTime(132);

        em.persist(member1);
        em.persist(movie);

        OrderItem orderItem = OrderItem.saveOrderItem(movie, 3, 13000);

        Order order = Order.saveOrder(member1,orderItem);

        orderRepository.save(order);

        OrderSearchForm orderSearchForm = new OrderSearchForm();
        orderSearchForm.setOrderStatus(OrderStatus.ORDER);
        orderSearchForm.setMemberName("member1");

        PageRequest pageRequest = PageRequest.of(0,3);
        Page<OrderDto> findOrder = orderRepository.search(orderSearchForm, pageRequest);

        assertThat(findOrder.getSize()).isEqualTo(3);
        assertThat(findOrder.getContent()).extracting("memberName").containsExactly("member1");
        System.out.println("findOrder = " + findOrder.getContent());
        System.out.println("findOrder = " + findOrder.hasNext());
        System.out.println("findOrder = " + findOrder.hasPrevious());
        System.out.println("findOrder = " + findOrder.getTotalPages());
    }
}
