/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.token;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Domain.model.PasswordResetToken;
import com.api.apibackend.modules.Auth.Infra.services.ITokenGeneratedResetPasswordService;

@Service
public class TokenGeneratedResetPasswordService implements ITokenGeneratedResetPasswordService {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static PasswordResetToken generateTokenWithExpiration() {
        byte[] randomBytes = new byte[6];
        SECURE_RANDOM.nextBytes(randomBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(24);
        
        return new PasswordResetToken(token, expirationDateTime);
    }
}
