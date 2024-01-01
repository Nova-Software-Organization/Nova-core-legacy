/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Stock.Domain.exception;

public class HasEnoughStockProductException extends Exception {
    public HasEnoughStockProductException(String message) {
        super(message);
    }
}
