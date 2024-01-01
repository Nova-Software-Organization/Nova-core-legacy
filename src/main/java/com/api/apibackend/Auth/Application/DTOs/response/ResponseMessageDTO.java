/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Auth.Application.DTOs.response;

import lombok.Data;

@Data
public class ResponseMessageDTO {
    private String message;
    private String className;
    private String errorMessage;
    private String accessToken;

    public ResponseMessageDTO(String message, String className, String errorMessage, String accessToken) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
        this.accessToken = accessToken;
    }
}
