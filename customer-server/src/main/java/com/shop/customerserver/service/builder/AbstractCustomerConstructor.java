package com.shop.customerserver.service.builder;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.role.Role;
import com.shop.customerserver.repository.CustomerRepository;
import com.shop.customerserver.repository.RoleRepository;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.CustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;

public abstract class AbstractCustomerConstructor implements ICustomerConstructor<Customer, CustomerDTO> {

    @Autowired
    protected CustomerRepository customerRepo;
    @Autowired
    protected RoleRepository roleRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public abstract Customer createCustomer(CustomerDTO customer);

    @Override
    public Customer createCustomer(CustomerDTO customerDTO, CustomerStatus status) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPatronymic(customerDTO.getPatronymic());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        Role role = roleRepo.findByName(customerDTO.getRole().name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        customer.setRole(role);
        customer.setStatus(status);
        return customer;
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(customerDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer is not found"));

        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setPatronymic(customerDTO.getPatronymic());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        Role role = roleRepo.findByName(customerDTO.getRole().name())
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        customer.setRole(role);
        customer.setStatus(customerDTO.getStatus());
        return customer;
    }

    @Override
    public abstract boolean findType(CustomerRole role);
}
