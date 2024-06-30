/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Auth.Domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PasswordResetToken {
    private String token;
    private LocalDateTime expirationDateTime;

    public PasswordResetToken(String token, LocalDateTime expirationDateTime) {
        this.token = token;
        this.expirationDateTime = expirationDateTime;
    }
}
