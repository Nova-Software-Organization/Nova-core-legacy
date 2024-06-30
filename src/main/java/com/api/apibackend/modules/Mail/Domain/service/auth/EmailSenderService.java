/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Mail.Domain.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Mail.Domain.service.repository.IEmailSenderResetPassword;
import com.api.apibackend.shared.Container.providers.MailProvider.implemations.AccountCreationNotificationMailProvider;
import com.api.apibackend.shared.Container.providers.MailProvider.implemations.ConfirmationMailProvider;
import com.api.apibackend.shared.Container.providers.MailProvider.implemations.ResetPasswordEmailProvider;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Profile("dev")
public class EmailSenderService implements IEmailSenderResetPassword {
    private JavaMailSender mailSender;
    private ResetPasswordEmailProvider resetPasswordEmailProvider;
    private AccountCreationNotificationMailProvider accountCreationNotificationMailProvider;
    private ConfirmationMailProvider confirmationMailProvider;

    @Autowired
    public EmailSenderService(
        JavaMailSender mailSender,
        ResetPasswordEmailProvider resetPasswordEmailProvider,
        AccountCreationNotificationMailProvider accountCreationNotificationMailProvider,
        ConfirmationMailProvider confirmationMailProvider
    ) {
        this.mailSender = mailSender;
        this.resetPasswordEmailProvider = resetPasswordEmailProvider;
        this.accountCreationNotificationMailProvider = accountCreationNotificationMailProvider;
        this.confirmationMailProvider = confirmationMailProvider;
    }
    
    public ResponseEntity<ResponseMessageDTO> sendEmail(String to, String resetCode, String templateType, String user) {
        Optional.ofNullable(to).ifPresent(value -> log.info("to: {}", value));
        Optional.ofNullable(resetCode).ifPresent(value -> log.info("resetCode: {}", value));
        Optional.ofNullable(templateType).ifPresent(value -> log.info("templateType: {}", value));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(to);
    
            switch (templateType) {
                case "reset-password":
                    resetPasswordEmailProvider.resetPasswordMail(helper, resetCode, templateType, user);
                    break;
                case "confirmation":
                    confirmationMailProvider.confirmationMail(helper, templateType, user);
                    break;
                case "account-creation-notification":
                    accountCreationNotificationMailProvider.accountCreationNotificationMail(helper, templateType, user);
                    break;
                default:
                    log.warn("Tipo de e-mail não suportado: {}", templateType);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseMessageDTO("Tipo de e-mail não suportado", this.getClass().getName(), null, null));
            }
    
            mailSender.send(message);
            log.info("E-mail enviado com sucesso para: {}", to);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessageDTO("Email enviado com sucesso!", this.getClass().getName(), null, null));
        } catch (MessagingException e) {
            log.error("Erro ao enviar e-mail", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage(), null));
        }
    }
}
