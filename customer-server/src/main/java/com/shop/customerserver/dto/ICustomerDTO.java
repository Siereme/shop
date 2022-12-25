package com.shop.customerserver.dto;

import com.shop.customerserver.utils.constant.CustomerRole;

public interface ICustomerDTO {
    String getName();

    String getSurname();

    String getPatronymic();

    String getEmail();

    String getPassword();

    String getPhone();

    CustomerRole getRole();

    void setName(String name);

    void setSurname(String surname);

    void setPatronymic(String patronymic);

    void setEmail(String email);

    void setPassword(String password);

    void setPhone(String phone);

    void setRole(CustomerRole role);
}
