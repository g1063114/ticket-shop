package project.ticket.shop.entity.item;

import lombok.Getter;
import lombok.Setter;
import project.ticket.shop.entity.BaseByEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {

    private String genre;
    private Integer runningTime;
}
