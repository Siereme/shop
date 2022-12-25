package com.shop.customerserver.service.builder.impl;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.service.builder.AbstractCustomerConstructor;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AnonymousCustomerConstructor extends AbstractCustomerConstructor {

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPatronymic(customerDTO.getPatronymic());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customer.setPhone(customerDTO.getPhone());
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
