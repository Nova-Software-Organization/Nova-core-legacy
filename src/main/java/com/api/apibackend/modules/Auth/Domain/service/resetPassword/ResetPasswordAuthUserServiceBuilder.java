package com.api.apibackend.modules.Auth.Domain.service.resetPassword;

import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Mail.Domain.service.EmailSenderServiceMail;
import org.springframework.stereotype.Service;

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