package com.api.apibackend.modules.Mail.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class EmailSenderServiceMail {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderServiceMail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String resetCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Redefinição de Senha - Código de Verificação");
        message.setText("Seu código de verificação para redefinição de senha é: " + resetCode);

        mailSender.send(message);
    }
}
