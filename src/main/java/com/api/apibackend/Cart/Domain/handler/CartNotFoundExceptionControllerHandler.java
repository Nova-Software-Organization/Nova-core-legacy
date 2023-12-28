package com.api.apibackend.Cart.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.Cart.Domain.exception.CartNotFoundException;

@ControllerAdvice
public class CartNotFoundExceptionControllerHandler {
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartException(CartNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
