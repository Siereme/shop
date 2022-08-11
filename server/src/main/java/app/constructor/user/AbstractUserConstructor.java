package app.constructor.user;

import app.model.user.IUser;
import app.model.user.role.Role;
import app.repository.user.UserRepository;
import app.repository.user.UserRoleRepository;
import app.utils.constants.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractUserConstructor<T extends IUser> implements IUserConstructor<T> {

    @Autowired
    protected UserRepository userRepo;
    @Autowired
    protected UserRoleRepository roleRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract T createUser(T user);

    public abstract T createUser(T user, UserStatus status);

    public T updateUser(T user) {
        T newUser = (T) userRepo.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findByName(user.getRole().getName())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        newUser.setRole(role);
        newUser.setStatus(user.getStatus());
        return newUser;
    }
}
