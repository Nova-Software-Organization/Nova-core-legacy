package com.api.apibackend.modules.Mail.Application.useCases.OrderCreated;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Mail.Application.repository.ISendEmail;

@Service
public class OrderCreatedUseCase  implements ISendEmail {

    @Override
    public void sendTextEmail(String to, String subject, String body) {
        throw new UnsupportedOperationException("Unimplemented method 'sendTextEmail'");
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        throw new UnsupportedOperationException("Unimplemented method 'sendHtmlEmail'");
    }

    @Override
    public void sendAttachmentEmail(String to, String subject, String body, String attachmentPath) {
        throw new UnsupportedOperationException("Unimplemented method 'sendAttachmentEmail'");
    }
}