package com.api.apibackend.Supplier.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class ErrorEmptySupplier extends Exception {
    public ErrorEmptySupplier(String message) {
        super(message);
    }
}
