package com.shop.orderserver.dto;

import com.shop.orderserver.utils.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    private String phone;

    private UserRole role;

}
