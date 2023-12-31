package com.api.apibackend.Cart.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }
}
