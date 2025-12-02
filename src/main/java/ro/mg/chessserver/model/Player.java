package ro.mg.chessserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Player {

    private int id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
