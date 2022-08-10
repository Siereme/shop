package app.constructor.user.impl;

import app.constructor.user.AbstractUserConstructor;
import app.exception.EntityNotFoundException;
import app.model.shoppingCart.ShoppingCart;
import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class AdminConstructor extends AbstractUserConstructor<User> {

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
        user.setShoppingCart(new ShoppingCart());
        return user;
    }
}
