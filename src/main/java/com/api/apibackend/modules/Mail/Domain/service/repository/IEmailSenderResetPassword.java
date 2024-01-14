/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Mail.Domain.service.repository;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;

public interface IEmailSenderResetPassword {
    ResponseEntity<ResponseMessageDTO> sendEmail(String to, String resetCode, String templateType, String user);
}
