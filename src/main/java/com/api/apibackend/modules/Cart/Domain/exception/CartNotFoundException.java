/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Cart.Domain.exception;

public class CartNotFoundException extends Exception {

    /**
     * Construtor para mensagem de erro.
     *
     * @param message Mensagem de erro.
     */
    public CartNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor para mensagem de erro e causa.
     *
     * @param message Mensagem de erro.
     * @param cause   A causa da exceção.
     */
    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
