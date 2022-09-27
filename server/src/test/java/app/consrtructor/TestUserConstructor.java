package app.consrtructor;

import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestUserConstructor {

    private final TestShoppingCartConstructor cartConstructor;
    private final TestOrderConstructor orderConstructor;
    private final List<User> userList = new ArrayList<>();

    public TestUserConstructor() {
        cartConstructor = new TestShoppingCartConstructor();
        orderConstructor = new TestOrderConstructor();
        construct();
    }

    private void construct() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Admin1");
        user1.setPatronymic("Admin1");
        user1.setSurname("Admin1");
        user1.setEmail("admin@mail.com");
        user1.setPassword("$2a$12$/GE5oRkYarA4Zsrf9l8vNOMhLxDK8B4mPI8zAaCmgoGz4R6Ptmwba");
        user1.setPhone("+79999999999");

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ADMIN");

        user1.setRole(role1);

        user1.setShoppingCart(cartConstructor.getById(1L));
        user1.setOrders(orderConstructor.getAllByIds(List.of(1L)));
        user1.setStatus(UserStatus.ACTIVE);


        User user2 = new User();
        user2.setId(2L);
        user2.setName("User1");
        user2.setPatronymic("User1");
        user2.setSurname("User1");
        user2.setEmail("user@mail.com");
        user2.setPassword("$2a$12$F11pS2k4m0.9KXlOiF5W0O8QZH2jHRqNLQ7fJatlJcR5zkBJvmI7S");
        user2.setPhone("+79999999998");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("USER");

        user2.setRole(role2);

        user2.setShoppingCart(cartConstructor.getById(2L));
        user2.setOrders(orderConstructor.getAllByIds(List.of(2L)));
        user2.setStatus(UserStatus.ACTIVE);

        userList.addAll(List.of(user1, user2));
    }

    public List<User> getAll() {
        return userList;
    }

    public User getById(Long id) {
        return userList.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst().orElseGet(User::new);
    }
}
