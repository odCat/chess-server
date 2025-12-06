package ro.mg.chessserver.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequest {

    private String usernameOrEmail;
    private String password;
}
