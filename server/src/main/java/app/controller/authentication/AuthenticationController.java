package app.controller.authentication;

import app.model.dto.user.AuthenticationUserDTO;
import app.model.user.User;
import app.model.dto.authentication.AuthenticationRequestDTO;
import app.security.JwtTokenProvider;
import app.service.authentication.AuthenticationService;
import app.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationUserDTO> authenticate(@RequestBody AuthenticationRequestDTO request){
        try{
            String email = request.getEmail();
            String password = request.getPassword();

            AuthenticationUserDTO authenticationUserDTO = authenticationService.authenticate(email, password);

            return ResponseEntity.ok().body(authenticationUserDTO);
        }catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        authenticationService.logout(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/info")
    public ResponseEntity<?> info(HttpServletRequest request){
        String token = request.getHeader(authorizationHeader);
        User user = userService.findByEmail(jwtTokenProvider.getUsername(token))
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        AuthenticationUserDTO authenticationUserDTO = new AuthenticationUserDTO(token, user);
        return ResponseEntity.ok().body(authenticationUserDTO);
    }
}
