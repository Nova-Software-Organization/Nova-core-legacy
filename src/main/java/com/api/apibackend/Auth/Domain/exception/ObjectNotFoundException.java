package com.api.apibackend.Auth.Domain.exception;

import jakarta.persistence.EntityNotFoundException;

public class ObjectNotFoundException extends EntityNotFoundException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
