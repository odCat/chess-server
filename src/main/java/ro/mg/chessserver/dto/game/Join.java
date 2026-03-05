package ro.mg.chessserver.dto.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Join {

    @NotNull(message = "Id should not be null")
    private long id;

    @NotBlank(message = "Color should not be blank")
    private String color;

    @NotBlank(message = "Player name should not be blank")
    private String name;
}
