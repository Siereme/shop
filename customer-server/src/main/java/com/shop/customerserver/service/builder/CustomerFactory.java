package com.shop.customerserver.service.builder;

import com.shop.customerserver.exception.UnknownConstructorTypeException;
import com.shop.customerserver.model.Customer;
import com.shop.customerserver.utils.constant.CustomerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomerFactory {

    @Autowired
    private Set<ICustomerConstructor<Customer>> constructors;

    public ICustomerConstructor<Customer> getFactory(CustomerRole role) {
        return constructors.stream()
                .filter(constructor -> constructor.findType(role))
                .findFirst().orElseThrow(UnknownConstructorTypeException::new);
    }
}
