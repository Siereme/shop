package app.constructor.user;

import app.exception.UnknownConstructorTypeException;
import app.model.user.User;
import app.utils.constants.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserFactory {

    @Autowired
    private Set<IUserConstructor<User>> constructors;

    public IUserConstructor<User> getFactory(UserRole role) {
        return constructors.stream()
                .filter(constructor -> constructor.findType(role))
                .findFirst().orElseThrow(UnknownConstructorTypeException::new);
    }
}
