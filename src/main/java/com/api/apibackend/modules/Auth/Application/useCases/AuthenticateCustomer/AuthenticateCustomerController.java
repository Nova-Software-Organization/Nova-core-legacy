/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases.AuthenticateCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Auth.Application.DTOs.response.LoginResponseDTO;
import com.api.apibackend.modules.Auth.Domain.model.LoginRequest;
import com.api.apibackend.modules.Customer.Domain.exception.ClientNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController
@RequestMapping("v1/auth")
public class AuthenticateCustomerController {
    private AuthenticateCustomerUseCase customerLoginUseCase;

    @Autowired
    public AuthenticateCustomerController(AuthenticateCustomerUseCase customerLoginUseCase) {
        this.customerLoginUseCase = customerLoginUseCase;
    }

    @PostMapping(path = "/entrar")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Login do usuariuo", description = "Informações de login do usuário")
    @Operation(summary = "Rota responsavel por efetuar o login de um usuario dentro da aplicação, desta forma disponibilazando que ele tenha acesso as outras paginas!")
    public ResponseEntity<LoginResponseDTO> handle(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            Optional.ofNullable(loginRequest)
                  .orElseThrow(() -> new IllegalArgumentException("Erro: dados de login não fornecidos"));
            return customerLoginUseCase.execute(loginRequest);
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponseDTO("Usuario não encontrado"));
        }
    }
}
