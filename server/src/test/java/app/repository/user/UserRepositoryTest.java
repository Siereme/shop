package app.repository.user;

import app.consrtructor.TestUserConstructor;
import app.model.order.Order;
import app.model.user.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SessionFactory sessionFactory;
    private final List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userList.addAll(new TestUserConstructor().getAll());
    }

    @Test
    void findById() {
        //Find user from repository where id = 2
        User user = userRepo.findById(2L).orElseGet(User::new);

        //Get user from constructor where id = 2
        User checkUser = userList.get(1);

        //Comparison of entities
        Assertions.assertEquals(checkUser.getId(), user.getId());
        Assertions.assertEquals(checkUser.getName(), user.getName());
        Assertions.assertEquals(checkUser.getPatronymic(), user.getPatronymic());
        Assertions.assertEquals(checkUser.getSurname(), user.getSurname());
        Assertions.assertEquals(checkUser.getEmail(), user.getEmail());
        Assertions.assertEquals(checkUser.getPhone(), user.getPhone());
        Assertions.assertEquals(checkUser.getPassword(), user.getPassword());
        Assertions.assertEquals(checkUser.getStatus(), user.getStatus());
        Assertions.assertEquals(checkUser.getRole().getId(), user.getRole().getId());
        Assertions.assertEquals(checkUser.getShoppingCart().getId(), user.getShoppingCart().getId());
    }

    @Test
    void findByEmail() {
        //Find user from repository where email = admin@mail.com
        User user = userRepo.findByEmail("admin@mail.com").orElseGet(User::new);

        //Get user from constructor where email = admin@mail.com
        User checkUser = userList.get(0);

        //Comparison of entities
        Assertions.assertEquals(checkUser.getId(), user.getId());
        Assertions.assertEquals(checkUser.getName(), user.getName());
        Assertions.assertEquals(checkUser.getPatronymic(), user.getPatronymic());
        Assertions.assertEquals(checkUser.getSurname(), user.getSurname());
        Assertions.assertEquals(checkUser.getEmail(), user.getEmail());
        Assertions.assertEquals(checkUser.getPhone(), user.getPhone());
        Assertions.assertEquals(checkUser.getPassword(), user.getPassword());
        Assertions.assertEquals(checkUser.getStatus(), user.getStatus());
        Assertions.assertEquals(checkUser.getRole().getId(), user.getRole().getId());
        Assertions.assertEquals(checkUser.getShoppingCart().getId(), user.getShoppingCart().getId());
    }
}