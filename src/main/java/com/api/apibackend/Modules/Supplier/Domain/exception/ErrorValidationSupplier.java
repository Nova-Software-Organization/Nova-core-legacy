package com.api.apibackend.Modules.Supplier.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class ErrorValidationSupplier extends Exception {
    public ErrorValidationSupplier(String message) {
        super(message);
    }
}
