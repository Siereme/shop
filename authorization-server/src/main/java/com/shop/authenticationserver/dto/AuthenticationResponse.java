package com.shop.authenticationserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {

    String accessToken;
    String refreshToken;
    UserDTO user;

    public AuthenticationResponse(UserDTO user) {
        this.user = user;
    }
}
