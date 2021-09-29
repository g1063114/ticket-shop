package project.ticket.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.ticket.shop.dto.OrderDto;
import project.ticket.shop.dto.OrderSearchForm;
import project.ticket.shop.entity.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    Page<OrderDto> search(OrderSearchForm orderSearchForm, Pageable pageable);
}
