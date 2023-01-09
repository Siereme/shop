package com.shop.customerserver.dto;

import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.permission.Permission;
import com.shop.customerserver.utils.constant.CustomerRole;
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
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String phone;
    private CustomerRole role;
    private Set<String> permissions;
    private CustomerStatus status;


    public UserDetailsDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.patronymic = customer.getPatronymic();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phone = customer.getPhone();
        this.role = CustomerRole.valueOf(customer.getRole().getName());
        this.permissions = customer.getRole().getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());
        this.status = customer.getStatus();
    }
}
