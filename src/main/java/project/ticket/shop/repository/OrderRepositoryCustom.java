package project.ticket.shop.repository;

import project.ticket.shop.dto.OrderDto;
import project.ticket.shop.dto.OrderSearchForm;
import project.ticket.shop.entity.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderDto> search(OrderSearchForm orderSearchForm);
}
