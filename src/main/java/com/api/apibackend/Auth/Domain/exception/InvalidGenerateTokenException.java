package com.api.apibackend.Auth.Domain.exception;

public class InvalidGenerateTokenException extends Exception {
    public InvalidGenerateTokenException(String menssage) {
        super(menssage);
    }
}
