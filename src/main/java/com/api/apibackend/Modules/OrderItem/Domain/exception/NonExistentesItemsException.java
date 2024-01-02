/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.OrderItem.Domain.exception;

public class NonExistentesItemsException extends Exception {
    public NonExistentesItemsException(String message) {
        super(message);
    }
}
