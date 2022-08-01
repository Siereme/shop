package app.service.authentication;

import app.model.dto.user.AuthenticationUserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthenticationService {

    AuthenticationUserDTO authenticate(String email, String password);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
