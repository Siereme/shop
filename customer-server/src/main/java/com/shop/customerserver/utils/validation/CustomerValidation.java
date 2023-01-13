package com.shop.customerserver.utils.validation;

import com.shop.customerserver.dto.CustomerDTO;
import com.shop.customerserver.exception.CustomerValidationException;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CustomerValidation {

    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenType}")
    private String tokenType;

    @Value("${jwt.accessHeader}")
    private String accessHeader;

    public void verifyCustomerCreate(CustomerDTO customer) {
        Map<String, String> messages = new HashMap<>();

        customerRepo.findByEmail(customer.getEmail())
                .ifPresent(verifyUserEmail -> messages.put("email", "Пользователь с таким email уже существует"));
        customerRepo.findByPhone(customer.getPhone())
                .ifPresent(verifyUserPhone -> messages.put("phone", "Пользователь с таким телефоном уже существует"));

        if (!messages.isEmpty()) {
            throw new CustomerValidationException(messages);
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
            throw new CustomerValidationException(messages);
        }
    }

    public boolean validateAccessToken(Jwt jwt) {
        String type = jwt.getClaims().get(tokenType).toString();
        Date expiration = Date.from((Instant) jwt.getClaims().get("exp"));
        return accessHeader.equals(type) && expiration.after(new Date());
    }

}
