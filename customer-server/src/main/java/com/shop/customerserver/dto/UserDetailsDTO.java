package com.shop.customerserver.dto;

import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.permission.Permission;
import com.shop.customerserver.utils.constant.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    private Long id;
    private String email;
    private String password;
    private Set<String> permissions;
    private CustomerStatus status;


    public UserDetailsDTO(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.permissions = customer.getRole().getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());
        this.status = customer.getStatus();
    }
}
