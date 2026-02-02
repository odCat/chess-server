package ro.mg.chessserver.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRequest {

    private String email;
    private String username;
    private String password;
    private String fullName;
}
