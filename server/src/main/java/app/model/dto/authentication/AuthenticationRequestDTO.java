package app.model.dto.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationRequestDTO {

    private String email;
    private String password;

}
