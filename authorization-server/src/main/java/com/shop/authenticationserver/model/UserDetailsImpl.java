package com.shop.authenticationserver.model;

import com.shop.authenticationserver.dto.UserDTO;
import com.shop.authenticationserver.utils.constant.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public UserDetailsImpl(Long id, String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    public UserDetailsImpl(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.authorities = userDTO.getAuthorities();
        this.isActive = userDTO.getStatus() != UserStatus.BANNED;
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

    public static UserDetails fromUser(UserDTO userDTO) {
        return new org.springframework.security.core.userdetails.User(
                userDTO.getEmail(), userDTO.getPassword(),
                userDTO.getStatus() != UserStatus.BANNED,
                userDTO.getStatus() != UserStatus.BANNED,
                userDTO.getStatus() != UserStatus.BANNED,
                userDTO.getStatus() != UserStatus.BANNED,
                userDTO.getAuthorities()
        );
    }
}
