/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.provider.resetPassword;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;

@Service
public class CodeExpiration {
    private UserRepository userRepository;

    @Autowired
    public CodeExpiration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Caso o token enviado enviado para usuario não for encontrado sera retornado true para dizer que o token foi expirado, a validação também será feita
     * no token caso seja encontrado com tempo máximo de 10 min
     * @param code
     * @return boolean
     */
    public boolean isCodeExpired(String code) {
        return userRepository.findByResetPasswordToken(code)
                .map(UserEntity::getResetPasswordTokenExpiration)
                .map(expiration -> expiration.isBefore(LocalDateTime.now().minusMinutes(10)))
                .orElse(true);
    }
}
