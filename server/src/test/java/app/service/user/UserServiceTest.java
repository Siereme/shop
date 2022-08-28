package app.service.user;

import app.consrtructor.TestUserConstructor;
import app.constructor.user.UserFactory;
import app.constructor.user.impl.AnonymousUserConstructor;
import app.constructor.user.impl.UserConstructor;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepo;
    @Mock
    private UserFactory userFactory;
    @Mock
    private UserConstructor userConstructor;
    @Mock
    private AnonymousUserConstructor anonymousConstructor;

    private final TestUserConstructor constructor = new TestUserConstructor();

    @Test
    void getUserRole() {
        //Prepare objects
        User user = constructor.getById(1L);
        UserRole userRoleCheck = UserRole.ADMIN;

        //Call a real service method
        UserRole userRole = userService.getUserRole(user);

        //Check the resulting object
        Assertions.assertEquals(userRoleCheck, userRole);
    }

    @Test
    void createUser() {
        //Prepare objects
        User userMock = new User();
        userMock.setId(101L);
        userMock.setEmail("newuser@mail.com");
        userMock.setPassword("1234");
        userMock.setPhone("+79999999999");
        userMock.setRole(constructor.getById(2L).getRole());
        userMock.setStatus(UserStatus.ACTIVE);

        //When stubs are called
        Mockito.when(userFactory.getFactory(UserRole.USER)).thenReturn(userConstructor);
        Mockito.when(userConstructor.createUser(userMock)).thenReturn(userMock);
        Mockito.when(userRepo.save(userMock)).thenReturn(userMock);

        //Call a real service method
        User user = userService.createUser(userMock);

        //Verify stub calls
        Mockito.verify(userFactory, Mockito.times(1)).getFactory(UserRole.USER);
        Mockito.verify(userConstructor, Mockito.times(1)).createUser(userMock);
        Mockito.verify(userRepo, Mockito.times(1)).save(userMock);

        //Check the resulting object
        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void createAnonymousUser() {
        //Prepare objects
        User userMock = new User();
        userMock.setId(101L);
        userMock.setEmail("newuser@mail.com");
        userMock.setPassword("1234");
        userMock.setPhone("+79999999999");
        userMock.setRole(constructor.getById(2L).getRole());
        userMock.setStatus(UserStatus.ANONYMOUS);

        //When stubs are called
        Mockito.when(userFactory.getFactory(UserRole.ANONYMOUS)).thenReturn(anonymousConstructor);
        Mockito.when(anonymousConstructor.createUser(any(User.class))).thenReturn(userMock);
        Mockito.when(userRepo.save(userMock)).thenReturn(userMock);

        //Call a real service method
        User user = userService.createAnonymousUser();

        //Verify stub calls
        Mockito.verify(userFactory, Mockito.times(1)).getFactory(UserRole.ANONYMOUS);
        Mockito.verify(anonymousConstructor, Mockito.times(1)).createUser(any(User.class));
        Mockito.verify(userRepo, Mockito.times(1)).save(userMock);

        //Check the resulting object
        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void updateUser() {
        //Prepare objects
        User userMock = new User();
        userMock.setId(101L);
        userMock.setEmail("newuser@mail.com");
        userMock.setPassword("1234");
        userMock.setPhone("+79999999999");
        userMock.setRole(constructor.getById(2L).getRole());
        userMock.setStatus(UserStatus.ACTIVE);

        //When stubs are called
        Mockito.when(userFactory.getFactory(UserRole.USER)).thenReturn(userConstructor);
        Mockito.when(userConstructor.updateUser(userMock)).thenReturn(userMock);

        //Call a real service method
        User user = userService.updateUser(userMock);

        //Verify stub calls
        Mockito.verify(userFactory, Mockito.times(1)).getFactory(UserRole.USER);
        Mockito.verify(userConstructor, Mockito.times(1)).updateUser(userMock);

        //Check the resulting object
        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void findById() {
        //Prepare objects
        User userMock = constructor.getById(2L);
        Optional<User> userOptional = Optional.of(userMock);

        //When stubs are called
        Mockito.when(userRepo.findById(2L)).thenReturn(userOptional);

        //Call a real service method
        User user = userService.findById(2L);

        //Verify stub calls
        Mockito.verify(userRepo, Mockito.times(1)).findById(2L);

        //Check the resulting object
        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }

    @Test
    void findByEmail() {
        //Prepare objects
        User userMock = constructor.getById(2L);
        Optional<User> userOptional = Optional.of(userMock);

        //When stubs are called
        Mockito.when(userRepo.findByEmail(userMock.getEmail())).thenReturn(userOptional);

        //Call a real service method
        User user = userService.findByEmail(userMock.getEmail());

        //Verify stub calls
        Mockito.verify(userRepo, Mockito.times(1)).findByEmail(userMock.getEmail());

        //Check the resulting object
        Assertions.assertEquals(userMock.getId(), user.getId());
        Assertions.assertEquals(userMock.getEmail(), user.getEmail());
        Assertions.assertEquals(userMock.getRole().getName(), user.getRole().getName());
        Assertions.assertEquals(userMock.getStatus(), user.getStatus());
    }
}