package ro.mg.chessserver.dto.player;

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
public class Register {

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
}
