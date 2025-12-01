package ro.mg.chessserver.model;

import lombok.ToString;


@ToString
public class Player {

    private int id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
