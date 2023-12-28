package com.api.apibackend.Order.Domain.exception;

public class OrderCannotBeCreatedException extends Exception {
    public OrderCannotBeCreatedException(String message) {
        super(message);
    }
}
