package com.shop.customerserver.utils.validation;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.exception.CustomerAlreadyExistsException;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CustomerValidation {

    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public void verifyCustomerCreate(CustomerDTO customer) {
        Map<String, String> messages = new HashMap<>();

        customerRepo.findByEmail(customer.getEmail())
                .ifPresent(verifyUserEmail -> messages.put("email", "Пользователь с таким email уже существует"));
        customerRepo.findByPhone(customer.getPhone())
                .ifPresent(verifyUserPhone -> messages.put("phone", "Пользователь с таким телефоном уже существует"));

        if (!messages.isEmpty()) {
            throw new CustomerAlreadyExistsException(messages);
        }
    }

    public void verifyLogin(Customer customer, String email, String password) {
        Map<String, String> messages = new HashMap<>();

        if (!Objects.equals(email, customer.getEmail())) {
            messages.put("email", "Пользователя с таким email не существует");
        } else if (!passwordEncoder.matches(password, customer.getPassword())) {
            messages.put("password", "Неверный пароль");
        }

        if (!messages.isEmpty()) {
            throw new CustomerAlreadyExistsException(messages);
        }
    }

}
