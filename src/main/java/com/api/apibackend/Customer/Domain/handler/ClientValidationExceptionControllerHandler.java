package com.api.apibackend.Customer.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.Customer.Domain.exception.ClientValidationException;

@ControllerAdvice
public class ClientValidationExceptionControllerHandler {
    @ExceptionHandler(ClientValidationException.class)
    public ResponseEntity<String> handleValidationNotFoundException(ClientValidationException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}