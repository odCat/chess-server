package ro.mg.chessserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "players")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    private int id;

    @NotBlank(message = "Email must not be blank")
    @Email
    private String email;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotNull(message = "Password must not be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=]).{8,24}$",
            message = "Password must have 8-24 characters and include at least a digit, a lowercase, an uppercase and a symbol")
    private String password;

    private String fullName;

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Player player))
            return false;

        return player.id == this.id || player.getEmail().equalsIgnoreCase(this.getEmail());
    }

    public boolean fullEquals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Player player))
            return false;

        return player.id == this.id &&
               player.getEmail().equalsIgnoreCase(this.getEmail()) &&
               player.getUsername().equalsIgnoreCase(this.getUsername()) &&
               player.getPassword().equals(this.getPassword()) &&
               player.getFullName().equalsIgnoreCase(this.getFullName())
        ;
    }
}
