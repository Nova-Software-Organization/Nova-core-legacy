package com.api.apibackend.modules.Mail.Application.useCases.AuthCreated;

import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Mail.Application.repository.ISendEmail;

@Service
public class AuthCreatedUseCase implements ISendEmail {

    @Override
    public void sendTextEmail(String to, String subject, String body) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendTextEmail'");
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendHtmlEmail'");
    }

    @Override
    public void sendAttachmentEmail(String to, String subject, String body, String attachmentPath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendAttachmentEmail'");
    }
}
