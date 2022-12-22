package com.shop.orderserver.exception;

public class UnknownConstructorTypeException extends RuntimeException {

    public UnknownConstructorTypeException() {
        super("Unknown user constructor type");
    }
}
