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

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Player player))
            return false;

        return player.id == this.id || player.getEmail().equalsIgnoreCase(this.getEmail());
    }
}
