package com.api.apibackend.Order.Domain.exception;

public class OrderCannotBeCreated extends Exception {
    public OrderCannotBeCreated(String message) {
        super(message);
    }
}
