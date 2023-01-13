package com.shop.authenticationserver.exception;

import java.util.HashMap;
import java.util.Map;

public class UserValidationException extends RuntimeException {
    Map<String, String> messages = new HashMap<>();

    public UserValidationException() {
        messages.put("email", "Пользователя с таким email не существует");
    }

    public UserValidationException(Map<String, String> messages) {
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessage(String name, String value) {
        this.messages.put(name, value);
    }
}
