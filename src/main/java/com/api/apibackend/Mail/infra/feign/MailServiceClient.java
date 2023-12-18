package com.api.apibackend.Mail.infra.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @FeignClient(name = "email-service", url = "http://localhost:8081/v1/email")
public interface MailServiceClient {
    
    @PostMapping(name = "/enviar")
    <T> ResponseEntity<String> sendEmail(@RequestBody T objectGeneric);
}
