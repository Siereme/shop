package app.service.user;

import app.consrtructor.TestUserConstructor;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserService userService;
    private final TestUserConstructor constructor = new TestUserConstructor();

    @Test
    void getUserRole() {
        User user = constructor.getById(1L);

        UserRole userRoleMock = UserRole.ADMIN;

        Mockito.when(userService.getUserRole(user)).thenReturn(userRoleMock);

        UserRole userRole = userService.getUserRole(user);

        Assertions.assertEquals(userRoleMock, userRole);
    }

    @Test
    void createUser() {
        User userMock = constructor.getById(2L);
        userMock.setId(101L);

        Mockito.when(userService.createUser(userMock)).thenReturn(userMock);

        User user = userService.createUser(userMock);

        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void createAnonymousUser() {
        User userMock = constructor.getById(2L);
        userMock.setId(101L);

        Mockito.when(userService.createAnonymousUser()).thenReturn(userMock);

        User user = userService.createAnonymousUser();

        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void updateUser() {
        User userMock = constructor.getById(2L);
        userMock.setId(101L);

        Mockito.when(userService.updateUser(userMock)).thenReturn(userMock);

        User user = userService.updateUser(userMock);

        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void findById() {
        User userMock = constructor.getById(2L);

        Mockito.when(userService.findById(2L)).thenReturn(userMock);

        User user = userService.findById(2L);

        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void findByEmail() {
        User userMock = constructor.getById(2L);

        Mockito.when(userService.findByEmail(userMock.getEmail())).thenReturn(userMock);

        User user = userService.findByEmail(userMock.getEmail());

        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }
}