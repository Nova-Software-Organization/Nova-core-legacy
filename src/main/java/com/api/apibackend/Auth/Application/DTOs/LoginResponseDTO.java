package com.api.apibackend.Auth.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}