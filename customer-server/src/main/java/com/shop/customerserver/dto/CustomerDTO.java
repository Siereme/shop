package com.shop.customerserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO implements ICustomerDTO {

    private Long id;
    @NotBlank(message = "Имя является обязательным")
    private String name;
    @NotBlank(message = "Фамилия является обязательной")
    private String surname;
    @NotBlank(message = "Отчество является обязательным")
    private String patronymic;
    @NotBlank(message = "Email является обязательным")
    private String email;

    private String lastEmail;
    @NotBlank(message = "Пароль является обязательным")
    private String password;
    @NotBlank(message = "Телефон является обязательным")
    private String phone;
    @Enumerated(EnumType.STRING)
    private CustomerRole role;
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;


    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.patronymic = customer.getPatronymic();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phone = customer.getPhone();
        this.role = CustomerRole.valueOf(customer.getRole().getName());
        this.status = customer.getStatus();
    }
}
