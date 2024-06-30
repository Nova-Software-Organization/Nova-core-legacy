/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.modules.Auth.Application.DTOs.request.LoginRequest;
import com.api.apibackend.modules.Auth.Application.DTOs.response.LoginResponseDTO;

public interface ICustomerController {
	ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest loginRequest) throws Exception;
}
