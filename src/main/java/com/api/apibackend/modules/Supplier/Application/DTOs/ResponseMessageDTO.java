/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.DTOs;

public class ResponseMessageDTO {
    private String message;
    private String className;
    private String errorMessage;

    public ResponseMessageDTO(String message, String className, String errorMessage) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
    }
}
