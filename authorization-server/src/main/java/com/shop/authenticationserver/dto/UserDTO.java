package com.shop.authenticationserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.authenticationserver.utils.constant.UserRole;
import com.shop.authenticationserver.utils.constant.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private List<String> permissions;
    private UserStatus status;


    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public void setPermissionsByAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.permissions = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

}
