package app.service.authentication;

import app.model.dto.user.AuthenticationUserResponse;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.security.JwtTokenProvider;
import app.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authService;
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    private final String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6Mywicm9sZSI6IkFOT05ZTU9VUyIsImlhdCI6MTY1OTc5NDk2NCwiZXhwIjoxNjU5Nzk1NTY5fQ.QBLRzUIs8HEsS4tdU_s2ecowYtTiMaX4K8T6XGqDwbY";
    private final String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRldEBtYWlsLmNvbSIsInJvbGUiOiJBTk9OWU1PVVMiLCJpYXQiOjE2NTk3OTQ5ODQsImV4cCI6MTY1OTc5NTU4OX0.fEgUjAsoRii0PfmK38QlVo6EkZstUXsWXuE5L-kREPQ";


    @Test
    void authenticate() {
        //Prepare objects
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        String email = "admin@mail.com";
        String password = "$2a$12$/GE5oRkYarA4Zsrf9l8vNOMhLxDK8B4mPI8zAaCmgoGz4R6Ptmwba";

        //When stubs are called
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        Mockito.when(userService.findByEmail(email)).thenReturn(user);
        Mockito.when(jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().getName())).thenReturn(accessToken);
        Mockito.when(jwtTokenProvider.createRefreshToken(user.getId(), user.getRole().getName())).thenReturn(refreshToken);

        //Call a real service method
        AuthenticationUserResponse response = authService.authenticate(email, password);

        //Verify stub calls
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        Mockito.verify(userService, Mockito.times(1)).findByEmail(email);
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createAccessToken(user.getEmail(), user.getRole().getName());
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createRefreshToken(user.getId(), user.getRole().getName());

        //Check the resulting object
        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void anonymousAuthenticate() {
        //Prepare objects
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        //When stubs are called
        Mockito.when(jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().getName())).thenReturn(accessToken);
        Mockito.when(jwtTokenProvider.createRefreshToken(user.getId(), user.getRole().getName())).thenReturn(refreshToken);

        //Call a real service method
        AuthenticationUserResponse response = authService.anonymousAuthenticate(user);

        //Verify stub calls
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createAccessToken(user.getEmail(), user.getRole().getName());
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createRefreshToken(user.getId(), user.getRole().getName());

        //Check the resulting object
        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void refreshAuthenticate() {
        //Prepare objects
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        //When stubs are called
        Mockito.when(jwtTokenProvider.getId(refreshToken)).thenReturn(1L);
        Mockito.when(userService.findById(1L)).thenReturn(user);
        Mockito.when(jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().getName())).thenReturn(accessToken);
        Mockito.when(jwtTokenProvider.createRefreshToken(user.getId(), user.getRole().getName())).thenReturn(refreshToken);

        //Call a real service method
        AuthenticationUserResponse response = authService.refreshAuthenticate(refreshToken);

        //Verify stub calls
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).getId(refreshToken);
        Mockito.verify(userService, Mockito.times(1)).findById(1L);
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createAccessToken(user.getEmail(), user.getRole().getName());
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createRefreshToken(user.getId(), user.getRole().getName());

        //Check the resulting object
        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void createToken() {
        //Prepare objects
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        //When stubs are called
        Mockito.when(jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().getName())).thenReturn(accessToken);
        Mockito.when(jwtTokenProvider.createRefreshToken(user.getId(), user.getRole().getName())).thenReturn(refreshToken);

        //Call a real service method
        AuthenticationUserResponse response = authService.createToken(user);

        //Verify stub calls
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createAccessToken(user.getEmail(), user.getRole().getName());
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).createRefreshToken(user.getId(), user.getRole().getName());

        //Check the resulting object
        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void logout() {
        //Get a service stub
        authService = Mockito.mock(AuthenticationService.class);

        //When the real method is called
        Mockito.doCallRealMethod().when(authService).logout(request, response);

        //Call a real service method
        authService.logout(request, response);

        //Verify stub calls
        Mockito.verify(authService, Mockito.times(1)).logout(request, response);
    }
}