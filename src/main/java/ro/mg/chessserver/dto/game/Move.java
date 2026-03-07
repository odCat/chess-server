package ro.mg.chessserver.dto.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Move
{
    @NotBlank(message = "Color should not be blank")
    private String color;

    @NotBlank(message = "From square should not be blank")
    private String from;

    @NotBlank(message = "To square should not be blank")
    private String to;

    private String promotion;

    @NotBlank(message = "Position should not be blank")
    private String fen;

    @NotBlank(message = "PGN should not be blank")
    private String pgn;
}
