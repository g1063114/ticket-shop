package project.ticket.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.entity.item.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
