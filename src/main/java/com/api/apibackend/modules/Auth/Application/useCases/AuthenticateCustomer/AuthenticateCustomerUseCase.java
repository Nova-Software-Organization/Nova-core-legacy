/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Auth.Application.useCases.AuthenticateCustomer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.response.LoginResponseDTO;
import com.api.apibackend.modules.Auth.Domain.authentication.AuthorizationLoginService;
import com.api.apibackend.modules.Auth.Domain.model.LoginRequest;

@Service
public class AuthenticateCustomerUseCase {
    private final AuthorizationLoginService authenticationLogin;

    @Autowired
    public AuthenticateCustomerUseCase(AuthorizationLoginService authenticationLogin) {
        this.authenticationLogin = authenticationLogin;
    }

    public ResponseEntity<LoginResponseDTO> execute(LoginRequest loginRequest) {
        try {
            Optional.ofNullable(loginRequest)
                    .orElseThrow(() -> new IllegalArgumentException("Erro: dados de login não fornecidos"));
            return authenticationLogin.login(loginRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDTO("Erro ao processar a solicitação de login"));
        }
    }
}
