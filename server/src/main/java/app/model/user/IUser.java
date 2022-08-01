package app.model.user;

import app.model.user.role.Role;
import app.utils.constants.user.UserStatus;

public interface IUser {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getPatronymic();

    void setPatronymic(String patronymic);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getPhone();

    void setPhone(String phone);

    Role getRole();

    void setRole(Role role);

    UserStatus getStatus();

    void setStatus(UserStatus status);

}
