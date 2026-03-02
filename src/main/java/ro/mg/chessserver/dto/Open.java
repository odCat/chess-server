package ro.mg.chessserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class Open {

    @NotBlank(message = "Color should not be blank")
    private String color;

    @NotBlank(message = "Player name should not be blank")
    private String name;
}
