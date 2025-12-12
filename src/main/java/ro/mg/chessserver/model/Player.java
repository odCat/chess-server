package ro.mg.chessserver.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int id;

    @NotNull(message = "Email must not be null")
    @NotBlank(message = "Email must not be empty")
    @Email
    private String email;

    @NotNull(message = "Username must not be null")
    @NotBlank(message = "Username must not be empty")
    private String username;

    @NotNull(message = "Password must not be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=]).{8,24}$",
            message = "Password must have 8-24 characters and include at least a digit, a lowercase, an uppercase and a symbol")
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

    public boolean fullEquals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Player player))
            return false;

        return player.id == this.id &&
               player.getEmail().equalsIgnoreCase(this.getEmail()) &&
               player.getUsername().equalsIgnoreCase(this.getUsername()) &&
               player.getPassword().equals(this.getPassword()) &&
               player.getFirstName().equalsIgnoreCase(this.getFirstName()) &&
               player.getLastName().equalsIgnoreCase(this.getLastName());
    }
}
