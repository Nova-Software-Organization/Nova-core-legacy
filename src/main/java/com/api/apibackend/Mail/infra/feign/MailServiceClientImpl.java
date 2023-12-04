package com.api.apibackend.Mail.infra.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MailServiceClientImpl implements MailServiceClient {

    private MailServiceClient mailServiceClient;

    public MailServiceClientImpl(MailServiceClient mailServiceClient) {
        this.mailServiceClient = mailServiceClient;
    }

    @Override
    public <T> ResponseEntity<String> sendEmail(T objectGeneric) {
        ResponseEntity<String> response = mailServiceClient.sendEmail(objectGeneric);
        return response;
    }
}
