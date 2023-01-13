package com.shop.customerserver.service;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.ICustomer;
import org.springframework.security.oauth2.jwt.Jwt;

import java.security.Principal;

public interface ICustomerService<T extends ICustomer> {

    T createCustomer(CustomerDTO customer);

    T updateCustomer(CustomerDTO customer);

    T createAnonymousCustomer();

    boolean isExist(Long id);

    Customer info(Jwt jwt, Principal principal);
}
