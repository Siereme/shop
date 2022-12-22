package com.shop.customerserver.service.builder.impl;

import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.service.builder.AbstractCustomerConstructor;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AdminConstructor extends AbstractCustomerConstructor {

    @Override
    public Customer createCustomer(Customer customer) {
        return this.createCustomer(customer, CustomerStatus.ACTIVE);
    }

    @Override
    public Customer createCustomer(Customer customer, CustomerStatus status) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setStatus(status);
        Role role = roleRepo.findByName(CustomerRole.ADMIN.name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        customer.setRole(role);
        return customer;
    }

    @Override
    public boolean findType(CustomerRole role) {
        return role == CustomerRole.ADMIN;
    }
}
