package project.ticket.shop.dto;

import lombok.Getter;
import lombok.Setter;
import project.ticket.shop.entity.OrderStatus;

@Getter
@Setter
public class OrderSearchForm {

    private String memberName;
    private OrderStatus orderStatus;
}
