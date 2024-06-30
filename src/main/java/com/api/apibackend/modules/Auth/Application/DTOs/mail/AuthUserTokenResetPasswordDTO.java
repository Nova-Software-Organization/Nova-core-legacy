/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.DTOs.mail;

import lombok.Data;

@Data
public class AuthUserTokenResetPasswordDTO {
    private String tokenGenerate;
    private String newPassword;
}
