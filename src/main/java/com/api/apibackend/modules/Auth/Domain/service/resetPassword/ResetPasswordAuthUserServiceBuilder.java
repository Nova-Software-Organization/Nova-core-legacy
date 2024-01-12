/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.service.resetPassword;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Mail.Domain.service.EmailSenderServiceMail;

@Service
public class ResetPasswordAuthUserServiceBuilder {
    private UserRepository userRepository;
    private EmailSenderServiceMail mailSendResetPassword;

    public ResetPasswordAuthUserServiceBuilder setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    public ResetPasswordAuthUserServiceBuilder setMailSendResetPassword(EmailSenderServiceMail mailSendResetPassword) {
        this.mailSendResetPassword = mailSendResetPassword;
        return this;
    }

}