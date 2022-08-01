package app.constructor.user.impl;

import app.constructor.user.AbstractUserConstructor;
import app.model.shoppingCart.ShoppingCart;
import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;

public class AdminConstructor extends AbstractUserConstructor<User> {

    @Override
    public User createUser(User user) {
        return createUser(user, UserStatus.ACTIVE);
    }

    @Override
    public User createUser(User user, UserStatus status) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(status);
        Role role = roleRepo.findByName(UserRole.ADMIN.name());
        user.setRole(role);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartRepo.save(shoppingCart);
        return user;
    }
}
