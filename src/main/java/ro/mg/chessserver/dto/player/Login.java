package ro.mg.chessserver.dto.player;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class Login {

    Long id;

    @NotBlank(message = "Email or username must not be blank")
    private String usernameOrEmail;

    @NotBlank(message = "Password must not be blank")
    private String password;
}
