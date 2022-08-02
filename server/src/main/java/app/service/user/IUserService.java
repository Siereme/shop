package app.service.user;

import app.model.user.IUser;

public interface IUserService<T extends IUser> {

    T createUser(T user);

    T updateUser(T newUser);

    T createAnonymousUser();

}
