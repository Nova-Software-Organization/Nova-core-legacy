/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Auth.Domain.exception;

import com.auth0.jwt.exceptions.JWTCreationException;

public class GenerateTokenErrorException extends JWTCreationException {
    public GenerateTokenErrorException(JWTCreationException e) {
        super(e.getMessage(), e);
    }
}
