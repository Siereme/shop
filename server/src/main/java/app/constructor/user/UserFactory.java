package app.constructor.user;

import app.constructor.user.impl.AdminConstructor;
import app.constructor.user.impl.AnonymousUserConstructor;
import app.constructor.user.impl.UserConstructor;
import app.exception.UnknownConstructorTypeException;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    @Autowired
    private ApplicationContext context;

    public IUserConstructor<User> getFactory(UserRole role) {
        if (role == UserRole.USER) {
            return context.getBean(UserConstructor.class);
        } else if (role == UserRole.ADMIN) {
            return context.getBean(AdminConstructor.class);
        } else if (role == UserRole.ANONYMOUS) {
            return context.getBean(AnonymousUserConstructor.class);
        } else {
            throw new UnknownConstructorTypeException();
        }
    }
}
