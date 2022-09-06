package app.model.dto.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Email является обязательным")
    private String email;
    @NotBlank(message = "Пароль является обязательным")
    private String password;

}
