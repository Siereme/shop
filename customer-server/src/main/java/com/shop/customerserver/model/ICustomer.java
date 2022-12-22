package com.shop.customerserver.model;

import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.utils.constant.CustomerStatus;

public interface ICustomer {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getPatronymic();

    void setPatronymic(String patronymic);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getPhone();

    void setPhone(String phone);

    Role getRole();

    void setRole(Role role);

    CustomerStatus getStatus();

    void setStatus(CustomerStatus status);

}
