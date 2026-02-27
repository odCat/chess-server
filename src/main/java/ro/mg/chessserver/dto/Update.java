package ro.mg.chessserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Update {

    private String email;
    private String username;
    private String password;
    private String fullName;
}
