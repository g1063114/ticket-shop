package project.ticket.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ticket.shop.entity.item.Snack;

public interface SnackRepository extends JpaRepository<Snack, Long> {
}
