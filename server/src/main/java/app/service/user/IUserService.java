package app.service.user;

import app.model.user.IUser;
import app.model.user.User;

public interface IUserService<T extends IUser> {

    T addUser(T user);

    T updateUser(T newUser);

    T addAnonymousUser();

}
