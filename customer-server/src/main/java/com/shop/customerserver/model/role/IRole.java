package com.shop.customerserver.model.role;

import com.shop.customerserver.model.Customer;
import com.shop.customerserver.model.permission.Permission;

import java.util.Set;

public interface IRole {
    Long getId();

    String getName();

    Set<Permission> getPermissions();

    Set<Customer> getCustomers();
}
