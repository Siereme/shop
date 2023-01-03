package com.shop.authenticationserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.authenticationserver.utils.constant.UserStatus;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private List<String> permissions;
    private UserStatus status;


    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
