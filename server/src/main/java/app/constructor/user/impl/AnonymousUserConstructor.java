package app.constructor.user.impl;

import app.constructor.user.AbstractUserConstructor;
import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
public class AnonymousUserConstructor extends AbstractUserConstructor {

    @Override
    public User createUser(User user) {
        return createUser(user, UserStatus.ANONYMOUS);
    }

    @Override
    public User createUser(User user, UserStatus status) {
        user.setName(UUID.randomUUID().toString());
        user.setSurname(UUID.randomUUID().toString());
        user.setPatronymic(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        user.setPhone(UUID.randomUUID().toString());
        user.setStatus(status);
        Role role = roleRepo.findByName(UserRole.ANONYMOUS.name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        user.setRole(role);
        return user;
    }

    @Override
    public boolean findType(UserRole role) {
        return role == UserRole.ANONYMOUS;
    }

}
