package com.api.apibackend.shared.Container.providers.MailProvider;

import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;

public interface IConfirmationAcctountMailProvider {
    void confirmationMail(MimeMessageHelper helper, String templateType, String user) throws MessagingException;
}
