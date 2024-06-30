package com.api.apibackend.modules.Mail.infra.feign;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @FeignClient(name = "email-service", url = "http://localhost:8081/v1/email")
public interface MailServiceClient {
    
    @PostMapping(name = "/enviar")
    <T> ResponseEntity<String> sendEmail(@RequestBody T objectGeneric);
}
