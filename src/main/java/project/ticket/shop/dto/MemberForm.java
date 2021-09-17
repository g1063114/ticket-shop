package project.ticket.shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "이름은 필수 입력 항목입니다.")
    private String username;

    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotNull
    @Range(min = 1, max = 100, message = "가능한 범위를 입력해주세요.")
    private Integer age;
}
