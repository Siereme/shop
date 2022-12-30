package com.shop.orderserver.utils.validation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Objects;

@Component
public class CustomerValidation {

    public boolean isValidCustomer(String email) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return Objects.equals(authentication.getName(), email);
    }

}
