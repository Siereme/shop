package com.shop.authenticationserver.controller;


import com.shop.authenticationserver.dto.AuthenticationResponse;
import com.shop.authenticationserver.exception.JwtAuthenticationException;
import com.shop.authenticationserver.exception.UserAlreadyExistsException;
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
import org.springframework.web.server.ResponseStatusException;

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
        try {
            AuthenticationResponse response = authService.login(email, password);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(path = "/login/update")
    public ResponseEntity<?> updateLogin(@RequestBody Map<String, String> user) throws JwtAuthenticationException {
        try {
            AuthenticationResponse response = authService.loginUpdate(user);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/login/anonymous")
    public ResponseEntity<?> anonymousAuthenticate() throws JwtAuthenticationException {
        try {
            AuthenticationResponse response = authService.loginAnonymous();
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<?> registration(@RequestBody Map<String, String> user) throws JwtAuthenticationException {
        try {
            AuthenticationResponse response = authService.registration(user);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refreshAuthenticate(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        try {
            AuthenticationResponse response = authService.refreshAuthentication(jwt, principal);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
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
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> handleUserAlreadyExistsExceptions(UserAlreadyExistsException ex) {
        return ex.getMessages();
    }

}
