package com.api.apibackend.Auth.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.nio.file.AccessDeniedException;

public class AuthorizationException extends AccessDeniedException {
    public AuthorizationException(String message) {
        super(message);
    }
}
