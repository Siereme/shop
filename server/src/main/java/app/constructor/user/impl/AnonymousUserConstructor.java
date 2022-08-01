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
        String hash = UUID.randomUUID().toString();
        user.setEmail(hash);
        user.setPassword(passwordEncoder.encode(hash));
        user.setStatus(status);
        Role role = roleRepo.findByName(UserRole.ANONYMOUS.name());
        user.setRole(role);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartRepo.save(shoppingCart);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User newUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPhone()));
        newUser.setRole(user.getRole());
        newUser.setStatus(user.getStatus());
        return newUser;
    }
}
