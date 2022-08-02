package app.service.authentication;

import app.model.dto.user.AuthenticationUserDTO;
import app.model.user.IUser;
import app.model.user.User;
import app.security.JwtTokenProvider;
import app.security.SecurityUser;
import app.service.user.UserService;
import app.utils.constants.user.UserStatus;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthenticationUserDTO authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return createToken(email);
    }

    public AuthenticationUserDTO anonymousAuthenticate(User user) {
        AnonymousAuthenticationToken authentication = new AnonymousAuthenticationToken(
                "anonymous-user-" + user.getEmail(),
                SecurityUser.fromUser(user),
                user.getRole().getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return createToken(user.getEmail());
    }

    public AuthenticationUserDTO createToken(String email){
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().getName());
        return new AuthenticationUserDTO(token, user);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.setClearAuthentication(true);
    }
}
