package project.ticket.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import project.ticket.shop.entity.Member;
import project.ticket.shop.entity.OrderItem;
import project.ticket.shop.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private String memberName;
    private String itemName;
    private Integer orderPrice;
    private Integer count;
    private OrderStatus status;
    private LocalDateTime orderDate;

    @QueryProjection
    public OrderDto(Long orderId, String memberName, String itemName, Integer orderPrice, Integer count, OrderStatus status, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.memberName = memberName;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
        this.status = status;
        this.orderDate = orderDate;
    }
}
