package app.service.user;

import app.constructor.user.IUserConstructor;
import app.constructor.user.UserFactory;
import app.model.user.User;
import app.repository.user.UserRepository;
import app.utils.constants.user.UserRole;
import app.utils.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService<User> {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private UserValidation userValidation;

    public UserRole getUserRole(User user) {
        return user.getRole() != null && user.getRole().getName() != null
                ? UserRole.valueOf(user.getRole().getName())
                : UserRole.USER;
    }

    public User createUser(User user) {
        userValidation.verifyUserCreate(user);
        UserRole role = getUserRole(user);
        IUserConstructor<User> constructor = userFactory.getFactory(role);
        User newUser = constructor.createUser(user);
        return userRepo.save(newUser);
    }

    public User createAnonymousUser() {
        User user = new User();
        IUserConstructor<User> constructor = userFactory.getFactory(UserRole.ANONYMOUS);
        User newUser = constructor.createUser(user);
        return userRepo.save(newUser);
    }

    public User updateUser(User user) {
        UserRole role = getUserRole(user);
        IUserConstructor<User> constructor = userFactory.getFactory(role);
        return constructor.updateUser(user);
    }

    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }
}
