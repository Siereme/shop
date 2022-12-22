package com.shop.customerserver.utils.constant;

public enum CustomerRole {
    ADMIN("ADMIN"),
    USER("USER"),
    ANONYMOUS("ANONYMOUS");

    private String value;

    CustomerRole(String value) {
        this.value = value;
    }
}
