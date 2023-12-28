package com.api.apibackend.Auth.Domain.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.apibackend.Auth.Domain.exception.AuthorizationException;

@ControllerAdvice
public class AccessDeniedExceptionHandlerController {
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<String> handleInvalidGenerateTokenFailedException(AuthorizationException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }
}
