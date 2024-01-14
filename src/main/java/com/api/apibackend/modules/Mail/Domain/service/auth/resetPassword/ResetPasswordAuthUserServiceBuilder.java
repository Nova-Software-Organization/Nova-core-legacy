/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Mail.Domain.service.auth.resetPassword;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;

@Service
public class ResetPasswordAuthUserServiceBuilder {
    private UserRepository userRepository;
    private EmailSenderService mailSendResetPassword;

    public ResetPasswordAuthUserServiceBuilder setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    public ResetPasswordAuthUserServiceBuilder setMailSendResetPassword(EmailSenderService mailSendResetPassword) {
        this.mailSendResetPassword = mailSendResetPassword;
        return this;
    }
}