package app.model.dto.user;

import app.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationUserResponse {

    private String accessToken;
    private String refreshToken;
    private User user;

    public AuthenticationUserResponse(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }
}
