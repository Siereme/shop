package app.controller.authentication;

import app.model.dto.authentication.AuthenticationRequestDTO;
import app.model.dto.user.AuthenticationUserDTO;
import app.model.user.User;
import app.security.JwtTokenProvider;
import app.service.authentication.AuthenticationService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String authorizationHeader;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationUserDTO> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            AuthenticationUserDTO authenticationUserDTO = authenticationService.authenticate(
                    request.getEmail(), request.getPassword()
            );
            return ResponseEntity.ok().body(authenticationUserDTO);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping(value = "/login-anonymous")
    public ResponseEntity<AuthenticationUserDTO> anonymousAuthenticate() {
        try {
            User user = userService.createAnonymousUser();
            AuthenticationUserDTO authenticationUserDTO = authenticationService.anonymousAuthenticate(user);
            return ResponseEntity.ok().body(authenticationUserDTO);
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
        String token = request.getHeader(authorizationHeader);
        User user = userService.findByEmail(jwtTokenProvider.getUsername(token));
        AuthenticationUserDTO authenticationUserDTO = new AuthenticationUserDTO(token, user);
        return ResponseEntity.ok().body(authenticationUserDTO);
    }
}
