package com.shop.customerserver.service.builder.impl;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.service.builder.AbstractCustomerConstructor;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomerConstructor extends AbstractCustomerConstructor {

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        customerDTO.setRole(CustomerRole.USER);
        return this.createCustomer(customerDTO, CustomerStatus.ACTIVE);
    }

    @Override
    public boolean findType(CustomerRole role) {
        return role == CustomerRole.USER;
    }
}
