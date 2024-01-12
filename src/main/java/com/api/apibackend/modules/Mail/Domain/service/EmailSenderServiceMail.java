package com.api.apibackend.modules.Mail.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.shared.container.providers.MailProvider.implemations.MailProvider;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@Profile("dev")
public class EmailSenderServiceMail {
    private JavaMailSender mailSender;
    private MailProvider mailProvider;

    @Autowired
    public EmailSenderServiceMail(JavaMailSender mailSender, MailProvider mailProvider) {
        this.mailSender = mailSender;
        this.mailProvider = mailProvider;
    }

    public ResponseEntity<ResponseMessageDTO> sendEmail(String to, String resetCode, String templateType) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(to);
            if ("reset-password".equals(templateType)) {
                mailProvider.resetPasswordMail(helper, resetCode, templateType);
            }

            mailSender.send(message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Email enviado com sucesso!", this.getClass().getName(), null, null));
        } catch (MessagingException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage(), null));
        }
    }
}
