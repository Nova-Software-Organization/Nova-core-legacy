/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Application.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Modules.Auth.Application.DTOs.response.LoginResponseDTO;
import com.api.apibackend.Modules.Auth.Domain.model.LoginRequest;

public interface ICustomerController {

	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest loginRequest) throws Exception;
}
