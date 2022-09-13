package app.constructor.user;

import app.model.user.IUser;
import app.model.user.User;
import app.utils.constants.user.UserStatus;

public interface IUserConstructor<T extends IUser> {

    T createUser(T user);

    T createUser(T user, UserStatus status);

    T updateUser(T user);

}
