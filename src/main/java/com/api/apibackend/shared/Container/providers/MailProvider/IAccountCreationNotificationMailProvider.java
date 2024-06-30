package com.api.apibackend.shared.Container.providers.MailProvider;

import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;

public interface IAccountCreationNotificationMailProvider {
    public void accountCreationNotificationMail(MimeMessageHelper helper, String templateType, String user) throws MessagingException;
}
