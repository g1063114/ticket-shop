package project.ticket.shop.entity.item;

import lombok.Getter;
import lombok.Setter;
import project.ticket.shop.entity.BaseByEntity;
import project.ticket.shop.entity.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    // 아이템 재고 감소
    public void removeStock(int stock){
        int currentStock = this.stock - stock;
        if( currentStock < 0 ){
            // 예외 처리
        }
        this.stock = currentStock;
    }
}
