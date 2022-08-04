package app.security;

import app.utils.constants.user.UserStatus;
import app.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SecurityUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(Long id, String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                !Objects.equals(user.getStatus(), UserStatus.BANNED.name()),
                !Objects.equals(user.getStatus(), UserStatus.BANNED.name()),
                !Objects.equals(user.getStatus(), UserStatus.BANNED.name()),
                !Objects.equals(user.getStatus(), UserStatus.BANNED.name()),
                user.getRole().getAuthorities()
        );
    }
}
