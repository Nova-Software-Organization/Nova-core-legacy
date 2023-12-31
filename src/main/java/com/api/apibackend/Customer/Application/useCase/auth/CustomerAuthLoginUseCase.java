package com.api.apibackend.Customer.Application.useCase.auth;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Application.DTOs.LoginResponseDTO;
import com.api.apibackend.Auth.Domain.authentication.AuthorizationLogin;
import com.api.apibackend.Auth.Domain.model.LoginRequest;

@Service
public class CustomerAuthLoginUseCase {
    private final AuthorizationLogin authenticationLogin;

    @Autowired
    public CustomerAuthLoginUseCase(AuthorizationLogin authenticationLogin) {
        this.authenticationLogin = authenticationLogin;
    }

    public ResponseEntity<LoginResponseDTO> execute(LoginRequest loginRequest) {
        try {
            if (loginRequest == null) {
                throw new IllegalArgumentException("Erro: dados de login não fornecidos");
            }
            return authenticationLogin.login(loginRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDTO("Erro ao processar a solicitação de login"));
        }
    }
}
