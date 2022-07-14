package app.model.dto.user;

import app.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationUserDTO {

    private String token;
    private User user;

    public AuthenticationUserDTO(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
