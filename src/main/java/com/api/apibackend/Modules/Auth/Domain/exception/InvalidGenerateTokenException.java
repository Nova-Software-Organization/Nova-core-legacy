package com.api.apibackend.Modules.Auth.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class InvalidGenerateTokenException extends Exception {
    public InvalidGenerateTokenException(String menssage) {
        super(menssage);
    }
}
