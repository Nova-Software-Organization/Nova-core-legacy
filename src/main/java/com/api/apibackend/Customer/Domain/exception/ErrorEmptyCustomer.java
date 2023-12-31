package com.api.apibackend.Customer.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class ErrorEmptyCustomer extends Exception {
    public ErrorEmptyCustomer(String message) {
        super(message);
    }
}
