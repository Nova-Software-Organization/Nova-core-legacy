package com.api.apibackend.Auth.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}
