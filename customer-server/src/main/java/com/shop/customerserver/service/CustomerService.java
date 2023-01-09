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
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.util.retry.Retry;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService implements ICustomerService<Customer> {
    private final WebClient.Builder webClientBuilder;
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
        webClientBuilder.build()
                .post().uri(ServiceUrl.CART_USER_ADD + newCustomer.getId())
                .retrieve()
                .bodyToMono(Void.class)
                .then()
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)));
        return newCustomer;
    }

    public Customer createAnonymousCustomer() {
        ICustomerConstructor<Customer, CustomerDTO> constructor = customerFactory.getFactory(CustomerRole.ANONYMOUS);
        Customer newCustomer = customerRepo.save(constructor.createCustomer(new CustomerDTO()));
        webClientBuilder.build()
                .post().uri(ServiceUrl.CART_USER_ADD + newCustomer.getId())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return newCustomer;
    }

    @Override
    public boolean isExist(Long id) {
        Long count = customerRepo.findCountById(id);
        return !count.equals(0L);
    }

    @Override
    public Customer info(Jwt jwt, Principal principal) {
        if (!customerValidation.validateAccessToken(jwt)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access token");
        }

        return customerRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Customer is not found"));
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
