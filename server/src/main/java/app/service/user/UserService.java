package app.service.user;

import app.constructor.user.AbstractUserConstructor;
import app.constructor.user.IUserConstructor;
import app.constructor.user.UserFactory;
import app.model.user.IUser;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.repository.user.UserRepository;
import app.repository.user.UserRoleRepository;
import app.utils.constants.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService<User> {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserFactory userFactory;

    public UserRole getUserRole(User user){
        return user.getRole() != null && user.getRole().getName() != null
                ? UserRole.valueOf(user.getRole().getName())
                : UserRole.USER;
    }

    public User addUser(User user) {
        UserRole role = getUserRole(user);
        IUserConstructor<User> constructor = userFactory.getFactory(role);
        User newUser = constructor.createUser(user);
        return userRepo.save(newUser);
    }

    public User updateUser(User newUser) {
        UserRole role = getUserRole(newUser);
        IUserConstructor<User> constructor = userFactory.getFactory(role);
        return constructor.updateUser(newUser);
    }

    public User findUser(User user) {
        return userRepo.findById(user.getId()).orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User addAnonymousUser() {
        User user = new User();
        IUserConstructor<User> constructor = userFactory.getFactory(UserRole.ANONYMOUS);
        User newUser = constructor.createUser(user);
        return userRepo.save(newUser);
    }
}
