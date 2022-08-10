package app.constructor.user.impl;

import app.constructor.user.AbstractUserConstructor;
import app.exception.EntityNotFoundException;
import app.model.shoppingCart.ShoppingCart;
import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnonymousUserConstructor extends AbstractUserConstructor<User> {

    @Override
    public User createUser(User user) {
        return createUser(user, UserStatus.ANONYMOUS);
    }

    @Override
    public User createUser(User user, UserStatus status) {
        user.setEmail(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        user.setStatus(status);
        Role role = roleRepo.findByName(UserRole.ANONYMOUS.name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        user.setRole(role);
        user.setShoppingCart(new ShoppingCart());
        return user;
    }

}
