package com.api.apibackend.OrderItem.Domain.exception;

public class NonExistentesItemsException extends Exception {
    public NonExistentesItemsException(String message) {
        super(message);
    }
}
