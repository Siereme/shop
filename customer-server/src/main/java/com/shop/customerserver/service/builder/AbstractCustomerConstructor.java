package com.shop.customerserver.service.builder;

import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.repository.CustomerRepository;
import com.shop.customerserver.repository.RoleRepository;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractCustomerConstructor implements ICustomerConstructor<Customer> {

    @Autowired
    protected CustomerRepository customerRepo;
    @Autowired
    protected RoleRepository roleRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public abstract Customer createCustomer(Customer customer);

    @Override
    public abstract Customer createCustomer(Customer customer, CustomerStatus status);

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer newCustomer = customerRepo.findById(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer is not found"));

        newCustomer.setName(customer.getName());
        newCustomer.setSurname(customer.getSurname());
        newCustomer.setPatronymic(customer.getPatronymic());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setEmail(customer.getEmail());
        Role role = roleRepo.findByName(customer.getRole().getName())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        newCustomer.setRole(role);
        newCustomer.setStatus(customer.getStatus());
        return newCustomer;
    }

    @Override
    public abstract boolean findType(CustomerRole role);
}
