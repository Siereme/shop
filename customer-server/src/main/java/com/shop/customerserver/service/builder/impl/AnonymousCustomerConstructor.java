package com.shop.customerserver.service.builder.impl;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.service.builder.AbstractCustomerConstructor;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
public class AnonymousCustomerConstructor extends AbstractCustomerConstructor {

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(UUID.randomUUID().toString());
        customer.setSurname(UUID.randomUUID().toString());
        customer.setPatronymic(UUID.randomUUID().toString());
        customer.setEmail(UUID.randomUUID().toString());
        customer.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        customer.setPhone(UUID.randomUUID().toString());
        customer.setStatus(CustomerStatus.ANONYMOUS);
        Role role = roleRepo.findByName(CustomerRole.ANONYMOUS.name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        customer.setRole(role);
        return customer;
    }


    @Override
    public boolean findType(CustomerRole role) {
        return role == CustomerRole.ANONYMOUS;
    }

}
