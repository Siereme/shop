package com.shop.customerserver.service;

import com.shop.customerserver.model.ICustomer;

import java.util.Optional;

public interface ICustomerService<T extends ICustomer> {

    T createCustomer(T customer);

    T updateCustomer(T customer);

    T createAnonymousCustomer();

    boolean isExist(Long id);
}
