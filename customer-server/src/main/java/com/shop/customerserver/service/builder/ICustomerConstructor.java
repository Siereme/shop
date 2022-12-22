package com.shop.customerserver.service.builder;

import com.shop.customerserver.model.ICustomer;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;

public interface ICustomerConstructor<T extends ICustomer> {

    T createCustomer(T customer);

    T createCustomer(T customer, CustomerStatus status);

    T updateCustomer(T customer);

    boolean findType(CustomerRole role);

}
