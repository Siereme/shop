package com.shop.authenticationserver.service;

import app.model.dto.user.AuthenticationUserResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthenticationService {

    AuthenticationUserResponse authenticate(String email, String password);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
