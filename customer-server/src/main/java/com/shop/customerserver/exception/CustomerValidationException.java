package com.shop.customerserver.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomerValidationException extends RuntimeException {
    Map<String, String> messages = new HashMap<>();

    public CustomerValidationException() {
    }

    public CustomerValidationException(Map<String, String> messages) {
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessage(String name, String value) {
        this.messages.put(name, value);
    }
}
