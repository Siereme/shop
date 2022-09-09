package app.controller.authentication;

import app.exception.JwtAuthenticationException;
import app.exception.UserAlreadyExistsException;
import app.model.dto.authentication.AuthenticationRequest;
import app.model.dto.user.AuthenticationUserResponse;
import app.model.user.User;
import app.security.JwtTokenProvider;
import app.service.authentication.AuthenticationService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<AuthenticationUserResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        try {
            AuthenticationUserResponse authenticationUserResponse = authenticationService.authenticate(
                    request.getEmail(), request.getPassword()
            );
            return ResponseEntity.ok().body(authenticationUserResponse);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping(value = "/login-anonymous")
    public ResponseEntity<AuthenticationUserResponse> anonymousAuthenticate() {
        try {
            User user = userService.createAnonymousUser();
            AuthenticationUserResponse authenticationUserResponse = authenticationService.anonymousAuthenticate(user);
            return ResponseEntity.ok().body(authenticationUserResponse);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<AuthenticationUserResponse> refreshAuthenticate(HttpServletRequest request) {
        try {
            String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
            if (refreshToken != null && jwtTokenProvider.validateRefreshToken(refreshToken)) {
                AuthenticationUserResponse authenticationUserResponse = authenticationService.refreshAuthenticate(refreshToken);
                return ResponseEntity.ok().body(authenticationUserResponse);
            }
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/info")
    public ResponseEntity<?> info(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        User user = userService.findByEmail(jwtTokenProvider.getUsername(accessToken));
        AuthenticationUserResponse authenticationUserResponse = new AuthenticationUserResponse(accessToken, refreshToken, user);
        return ResponseEntity.ok().body(authenticationUserResponse);
    }

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
