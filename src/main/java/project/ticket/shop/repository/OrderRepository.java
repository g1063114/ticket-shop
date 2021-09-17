package project.ticket.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ticket.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
