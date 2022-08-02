package app.constructor.user.impl;

import app.constructor.user.AbstractUserConstructor;
import app.exception.EntityNotFoundException;
import app.model.shoppingCart.ShoppingCart;
import app.model.user.User;
import app.model.user.role.Role;
import app.utils.constants.user.UserRole;
import app.utils.constants.user.UserStatus;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
        user.setRole(roleRepo.findByName(UserRole.ANONYMOUS.name()));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartRepo.save(shoppingCart);
        return user;
    }

    public User updateUser(User user){
        User newUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User is not found"));

        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(roleRepo.findByName(UserRole.ANONYMOUS.name()));
        newUser.setStatus(user.getStatus());
        return newUser;
    }

}
