package app.model.user.role;

import app.model.user.User;
import app.model.user.permission.Permission;

import java.util.Set;

public interface IRole {
    Long getId();

    String getName();

    Set<Permission> getPermissions();

    Set<User> getUsers();
}
