package com.shop.customerserver.service;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.repository.CustomerRepository;
import com.shop.customerserver.service.builder.CustomerFactory;
import com.shop.customerserver.service.builder.ICustomerConstructor;
import com.shop.customerserver.utils.constant.CustomerRole;
import com.shop.customerserver.utils.constant.ServiceUrl;
import com.shop.customerserver.utils.validation.CustomerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements ICustomerService<Customer> {

    private final RestTemplate restTemplate;
    private final CustomerRepository customerRepo;
    private final CustomerFactory customerFactory;
    private final CustomerValidation customerValidation;

    public CustomerRole getCustomerRole(CustomerDTO customer) {
        return customer.getRole() != null ? customer.getRole() : CustomerRole.USER;
    }

    public Customer createCustomer(CustomerDTO customer) {
        customerValidation.verifyCustomerCreate(customer);
        CustomerRole role = getCustomerRole(customer);
        ICustomerConstructor<Customer, CustomerDTO> constructor = customerFactory.getFactory(role);
        Customer newCustomer = customerRepo.save(constructor.createCustomer(customer));
        restTemplate.postForLocation(ServiceUrl.CART_USER_ADD + newCustomer.getId(), null);
        return newCustomer;
    }

    public Customer createAnonymousCustomer() {
        ICustomerConstructor<Customer, CustomerDTO> constructor = customerFactory.getFactory(CustomerRole.ANONYMOUS);
        Customer newCustomer = customerRepo.save(constructor.createCustomer(new CustomerDTO()));
        restTemplate.postForLocation(ServiceUrl.CART_USER_ADD + newCustomer.getId(), null);
        return newCustomer;
    }

    @Override
    public boolean isExist(Long id) {
        Long count = customerRepo.findCountById(id);
        return !count.equals(0L);
    }

    public Customer updateCustomer(CustomerDTO customer) {
        CustomerRole role = getCustomerRole(customer);
        ICustomerConstructor<Customer, CustomerDTO> constructor = customerFactory.getFactory(role);
        return constructor.updateCustomer(customer);
    }

    public Customer findById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist"));
    }

    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer doesn't exist"));
    }
}
