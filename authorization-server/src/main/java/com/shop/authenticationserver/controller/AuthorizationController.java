package com.shop.authenticationserver.controller;


import com.shop.authenticationserver.dto.AuthenticationResponse;
import com.shop.authenticationserver.exception.JwtAuthenticationException;
import com.shop.authenticationserver.exception.UserPersistenceException;
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
    public ResponseEntity<AuthenticationResponse> login(@RequestParam String email, @RequestParam String password) throws JwtAuthenticationException {
        AuthenticationResponse response = authService.login(email, password);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/login/update")
    public ResponseEntity<AuthenticationResponse> updateLogin(@RequestBody Map<String, String> user) throws JwtAuthenticationException {
        AuthenticationResponse response = authService.loginUpdate(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/login/anonymous")
    public ResponseEntity<AuthenticationResponse> anonymousAuthenticate() throws JwtAuthenticationException {
        AuthenticationResponse response = authService.loginAnonymous();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<AuthenticationResponse> registration(@RequestBody Map<String, String> user) throws JwtAuthenticationException {
        AuthenticationResponse response = authService.registration(user);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<AuthenticationResponse> refreshAuthenticate(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        AuthenticationResponse response = authService.refreshAuthentication(jwt, principal);
        return ResponseEntity.ok().body(response);
    }

//    @PostMapping(value = "/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//        authenticationService.logout(request, response);
//        return ResponseEntity.ok().build();
//    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserPersistenceException.class)
    public Map<String, String> handleUserAlreadyExistsExceptions(UserPersistenceException ex) {
        return ex.getMessages();
    }

}
