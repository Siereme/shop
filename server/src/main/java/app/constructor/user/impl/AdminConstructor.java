package app.constructor.user.impl;

import app.constructor.user.AbstractUserConstructor;
import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AdminConstructor extends AbstractUserConstructor {

    @Override
    public User createUser(User user) {
        return createUser(user, UserStatus.ACTIVE);
    }

    @Override
    public User createUser(User user, UserStatus status) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(status);
        Role role = roleRepo.findByName(UserRole.ADMIN.name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        user.setRole(role);
        return user;
    }

    @Override
    public boolean findType(UserRole role) {
        return role == UserRole.ADMIN;
    }
}
