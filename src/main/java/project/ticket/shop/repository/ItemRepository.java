package project.ticket.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ticket.shop.entity.item.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
