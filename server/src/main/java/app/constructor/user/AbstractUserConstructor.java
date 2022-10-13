package app.constructor.user;

import app.model.user.User;
import app.model.user.role.Role;
import app.repository.user.UserRepository;
import app.repository.user.UserRoleRepository;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractUserConstructor implements IUserConstructor<User> {

    @Autowired
    protected UserRepository userRepo;
    @Autowired
    protected UserRoleRepository roleRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public abstract User createUser(User user);

    @Override
    public abstract User createUser(User user, UserStatus status);

    @Override
    public User updateUser(User user) {
        User newUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        Role role = roleRepo.findByName(user.getRole().getName())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        newUser.setRole(role);
        newUser.setStatus(user.getStatus());
        return newUser;
    }

    @Override
    public abstract boolean findType(UserRole role);
}
