/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ContactNewsletter.Application.repository;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ContactDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface IContact {
    ResponseEntity<?> toReceiveContact(@RequestBody ContactDTO contactRequest);
}
