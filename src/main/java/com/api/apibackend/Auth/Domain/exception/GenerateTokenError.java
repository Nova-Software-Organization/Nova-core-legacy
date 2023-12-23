package com.api.apibackend.Auth.Domain.exception;

import com.auth0.jwt.exceptions.JWTCreationException;

public class GenerateTokenError extends JWTCreationException {
    public GenerateTokenError(JWTCreationException e) {
        super(e.getMessage(), e);
    }
}
