package com.shop.authenticationserver.controller;


import com.shop.authenticationserver.dto.AuthenticationResponse;
import com.shop.authenticationserver.exception.JwtAuthenticationException;
import com.shop.authenticationserver.exception.UserValidationException;
import com.shop.authenticationserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {


    private final AuthenticationService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        AuthenticationResponse response = authService.login(email, password);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/login/update")
    public ResponseEntity<?> updateLogin(@RequestBody Map<String, String> user) throws JwtAuthenticationException {
        AuthenticationResponse response = authService.loginUpdate(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/login/anonymous")
    public ResponseEntity<?> anonymousAuthenticate() throws JwtAuthenticationException {
        AuthenticationResponse response = authService.loginAnonymous();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<?> registration(@RequestBody Map<String, String> user) throws JwtAuthenticationException {
        AuthenticationResponse response = authService.registration(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refreshAuthenticate(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        AuthenticationResponse response = authService.refreshAuthentication(jwt, principal);
        return ResponseEntity.ok().body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsExceptions(UserValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
    }

}
