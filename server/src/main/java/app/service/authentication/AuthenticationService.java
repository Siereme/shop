package app.service.authentication;

import app.model.dto.user.AuthenticationUserResponse;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.security.JwtTokenProvider;
import app.security.SecurityUser;
import app.service.user.UserService;
import app.utils.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserValidation userValidation;


    public AuthenticationUserResponse authenticate(String email, String password) {
        User user = userRepo.findByEmail(email).orElseGet(User::new);
        userValidation.verifyLogin(user, email, password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return createToken(user);
    }

    public AuthenticationUserResponse anonymousAuthenticate(User user) {
        AnonymousAuthenticationToken authentication = new AnonymousAuthenticationToken(
                "anonymous-user-" + user.getEmail(),
                SecurityUser.fromUser(user),
                user.getRole().getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return createToken(user);
    }

    public AuthenticationUserResponse refreshAuthenticate(String refreshToken) {
        User user = userRepo.findById(jwtTokenProvider.getId(refreshToken))
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        SecurityContextHolder.clearContext();
        return createToken(user);
    }

    public AuthenticationUserResponse createToken(User user) {
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().getName());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getRole().getName());
        return new AuthenticationUserResponse(accessToken, refreshToken, user);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.setClearAuthentication(true);
    }
}
