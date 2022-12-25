package com.shop.customerserver.service;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.ICustomer;

import java.util.Optional;

public interface ICustomerService<T extends ICustomer> {

    T createCustomer(CustomerDTO customer);

    T updateCustomer(CustomerDTO customer);

    T createAnonymousCustomer();

    boolean isExist(Long id);
}
