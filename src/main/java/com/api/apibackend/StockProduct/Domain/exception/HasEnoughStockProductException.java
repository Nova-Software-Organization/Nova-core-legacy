package com.api.apibackend.StockProduct.Domain.exception;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class HasEnoughStockProductException extends Exception {
    public HasEnoughStockProductException(String message) {
        super(message);
    }
}
