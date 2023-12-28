package com.api.apibackend.Auth.Domain.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DataBindingViolationException extends DataIntegrityViolationException {
    public DataBindingViolationException(String message) {
        super(message);
    }
}
