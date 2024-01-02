/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Domain.exception;

public class ErrorEmptyCustomer extends Exception {
    public ErrorEmptyCustomer(String message) {
        super(message);
    }
}
