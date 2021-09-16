package project.ticket.shop.entity.item;

import lombok.Getter;
import lombok.Setter;
import project.ticket.shop.entity.BaseByEntity;
import project.ticket.shop.entity.BaseTimeEntity;

import javax.persistence.*;

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

    private String name;
    private int price;
    private int stock;
}
