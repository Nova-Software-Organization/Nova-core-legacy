package com.api.apibackend.Order.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class OrderCannotBeCreatedException extends Exception {
    public OrderCannotBeCreatedException(String message) {
        super(message);
    }
}
