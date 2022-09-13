package app.constructor.user;

import app.constructor.user.impl.AdminConstructor;
import app.constructor.user.impl.AnonymousUserConstructor;
import app.constructor.user.impl.UserConstructor;
import app.exception.UnknownConstructorTypeException;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    @Autowired
    private UserConstructor userConstructor;
    @Autowired
    private AdminConstructor adminConstructor;
    @Autowired
    private AnonymousUserConstructor anonymousConstructor;

    public IUserConstructor<User> getFactory(UserRole role) {
        if (role == UserRole.USER) {
            return userConstructor;
        } else if (role == UserRole.ADMIN) {
            return adminConstructor;
        } else if (role == UserRole.ANONYMOUS) {
            return anonymousConstructor;
        } else {
            throw new UnknownConstructorTypeException();
        }
    }
}
