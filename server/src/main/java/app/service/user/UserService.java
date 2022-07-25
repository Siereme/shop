package app.service.user;

import app.model.shoppingCart.ShoppingCart;
import app.model.user.Role;
import app.model.user.User;
import app.model.user.UserStatus;
import app.repository.shoppingCart.ShoppingCartRepository;
import app.repository.user.UserRepository;
import app.repository.user.UserRoleRepository;
import app.service.shoppingCart.ShoppingCartService;
import app.utils.constants.UserVariables;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
    private final ShoppingCartRepository cartRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, UserRoleRepository roleRepo, ShoppingCartService cartService, ShoppingCartRepository cartRepo, PasswordEncoder passwordEncoder, UserRoleRepository roleRepo1, ShoppingCartRepository cartRepo1, PasswordEncoder passwordEncoder1) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo1;
        this.cartRepo = cartRepo1;
        this.passwordEncoder = passwordEncoder1;
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(User newUser) {
        User user = userRepo.findById(newUser.getId()).orElse(null);
        if(user == null) return null;
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setPatronymic(newUser.getPatronymic());
        user.setPhone(newUser.getPhone());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPhone()));
        user.setStatus(newUser.getStatus());
        return user;
    }

    public User findUser(User user) {
        return userRepo.findById(user.getId()).orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User addAnonymousUser() {
        User user = new User();
        String hash = UUID.randomUUID().toString();
        user.setEmail(hash);
        user.setPassword(passwordEncoder.encode(hash));
        user.setStatus(UserStatus.ANONYMOUS.name());
        Role role = roleRepo.findByName(UserVariables.ROLE_USER);
        user.setRole(role);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartRepo.save(shoppingCart);
        return userRepo.save(user);
    }
}
