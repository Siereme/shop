package com.shop.customerserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.utils.constant.CustomerStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
@Getter
@Setter
@RequiredArgsConstructor
public class Customer implements ICustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Имя является обязательным")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Фамилия является обязательной")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Отчество является обязательным")
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank(message = "Email является обязательным")
//    @Email(message = "Неправельный формат email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Пароль является обязательным")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Телефон является обязательным")
    @Column(name = "phone", unique = true)
    private String phone;

    @JsonIgnoreProperties(value = {"users", "permissions", "authorities"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

}
