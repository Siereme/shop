package app.service.authentication;

import app.model.dto.user.AuthenticationUserResponse;
import app.model.user.User;
import app.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class AuthenticationServiceTest {

    @Mock
    private AuthenticationService authService;
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
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        String email = "admin@mail.com";
        String password = "$2a$12$/GE5oRkYarA4Zsrf9l8vNOMhLxDK8B4mPI8zAaCmgoGz4R6Ptmwba";

        Mockito.when(authService.authenticate(email, password)).thenReturn(responseMock);

        AuthenticationUserResponse response = authService.authenticate(email, password);

        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void anonymousAuthenticate() {
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        Mockito.when(authService.anonymousAuthenticate(user)).thenReturn(responseMock);

        AuthenticationUserResponse response = authService.anonymousAuthenticate(user);

        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void refreshAuthenticate() {
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        Mockito.when(authService.refreshAuthenticate(refreshToken)).thenReturn(responseMock);

        AuthenticationUserResponse response = authService.refreshAuthenticate(refreshToken);

        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void createToken() {
        User user = userRepo.findById(1L).orElseGet(User::new);
        AuthenticationUserResponse responseMock = new AuthenticationUserResponse(
                accessToken,
                refreshToken,
                user
        );

        Mockito.when(authService.createToken(user)).thenReturn(responseMock);

        AuthenticationUserResponse response = authService.createToken(user);

        Assertions.assertEquals(responseMock.getAccessToken(), response.getAccessToken());
        Assertions.assertEquals(responseMock.getRefreshToken(), response.getRefreshToken());
        Assertions.assertEquals(responseMock.getUser().getEmail(), response.getUser().getEmail());
    }

    @Test
    void logout() {
        Mockito.doCallRealMethod().when(authService).logout(request, response);

        authService.logout(request, response);

        Mockito.verify(authService, Mockito.times(1)).logout(request, response);
    }
}