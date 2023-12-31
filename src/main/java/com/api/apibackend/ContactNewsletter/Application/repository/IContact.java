package com.api.apibackend.ContactNewsletter.Application.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.http.ResponseEntity;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface IContact {
    ResponseEntity<?> toReceiveContact(@RequestBody ContactRequest contactRequest);
}
