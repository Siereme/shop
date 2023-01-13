package com.shop.authenticationserver.utils.validation;

import com.shop.authenticationserver.exception.UserValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final PasswordEncoder passwordEncoder;

    public void validateLogin(UserDetails user, String email, String password) {
        Map<String, String> messages = new HashMap<>();

        if (!Objects.equals(email, user.getUsername())) {
            messages.put("email", "Неверный email");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            messages.put("password", "Неверный пароль");
        }

        if (!messages.isEmpty()) {
            throw new UserValidationException(messages);
        }
    }


}
