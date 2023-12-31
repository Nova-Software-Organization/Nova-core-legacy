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
public class AuthAccessToken {
    private String access_token;
}
