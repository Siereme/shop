package com.shop.customerserver.service.builder;

import com.shop.customerserver.dto.ICustomerDTO;
import com.shop.customerserver.model.ICustomer;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;

public interface ICustomerConstructor<C extends ICustomer, T extends ICustomerDTO> {

    C createCustomer(T customer);

    C createCustomer(T customer, CustomerStatus role);

    C updateCustomer(T customer);

    boolean findType(CustomerRole role);

}
