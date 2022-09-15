package app.constructor.user;

import app.constructor.user.impl.AdminConstructor;
import app.constructor.user.impl.AnonymousUserConstructor;
import app.constructor.user.impl.UserConstructor;
import app.exception.UnknownConstructorTypeException;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserFactory {

    private final Set<IUserConstructor<User>> constructors;

    public UserFactory(UserConstructor userConstructor,
                       AdminConstructor adminConstructor,
                       AnonymousUserConstructor anonymousConstructor) {
        this.constructors = Set.of(userConstructor, adminConstructor, anonymousConstructor);
    }

    public IUserConstructor<User> getFactory(UserRole role) {
        return constructors.stream()
                .filter(constructor -> constructor.findType(role))
                .findFirst().orElseThrow(UnknownConstructorTypeException::new);
    }
}
