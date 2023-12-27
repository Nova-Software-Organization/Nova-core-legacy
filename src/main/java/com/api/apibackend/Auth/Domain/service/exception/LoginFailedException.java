package com.api.apibackend.Auth.Domain.service.exception;

public class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}
