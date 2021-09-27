package project.ticket.shop.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.ticket.shop.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseByEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;
    private int orderPrice;

    // 주문 아이템 생성 메서드
    public static OrderItem saveOrderItem(Item item, int count, int orderPrice){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(orderPrice * count);

        item.removeStock(count);

        return orderItem;
    }

    // 주문 아이템 취소
    public void cancel(){
        getItem().addStock(count);
    }
}
