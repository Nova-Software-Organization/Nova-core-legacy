package com.api.apibackend.Auth.Domain.exception;

import com.auth0.jwt.exceptions.JWTCreationException;

public class GenerateTokenErrorException extends JWTCreationException {
    public GenerateTokenErrorException(JWTCreationException e) {
        super(e.getMessage(), e);
    }
}
