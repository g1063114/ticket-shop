package project.ticket.shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemForm {

    @NotEmpty(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 항목입니다.")
    private Integer price;

    @NotNull(message = "좌석수, 수량은 필수 입력 항목입니다.")
    private Integer stock;

    private String genre;

    @Range(min = 1, max = 1000)
    private Integer runningTime;

    private String category;
}
