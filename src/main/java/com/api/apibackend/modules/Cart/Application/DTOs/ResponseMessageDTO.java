/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Cart.Application.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class ResponseMessageDTO {
    private String message;
    private String className;
    private String errorMessage;
    private List<Long> errorMessageProducts;

    public ResponseMessageDTO(String message, String className, String errorMessage) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
    }

    public ResponseMessageDTO(String message, String className, String errorMessage, List<Long> errorMessageProducts) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
        this.errorMessageProducts = errorMessageProducts;
    }
}
