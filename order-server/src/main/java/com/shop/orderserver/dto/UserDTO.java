package com.shop.orderserver.dto;

import com.shop.orderserver.utils.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Имя является обязательным")
    private String name;

    @NotBlank(message = "Фамилия является обязательной")
    private String surname;

    @NotBlank(message = "Отчество является обязательным")
    private String patronymic;

    @NotBlank(message = "Email является обязательным")
    private String email;

    @NotBlank(message = "Email является обязательным")
    private String lastEmail;

    @NotBlank(message = "Пароль является обязательным")
    private String phone;

    private UserRole role;

}
