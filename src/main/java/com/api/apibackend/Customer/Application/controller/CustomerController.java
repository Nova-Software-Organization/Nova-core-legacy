package com.api.apibackend.Customer.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Auth.Application.DTOs.ValidateTokenRequest;
import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Auth.Domain.service.exception.RegistrationFailedException;
import com.api.apibackend.Customer.Application.DTOs.registration.RegistrationRequest;
import com.api.apibackend.Customer.Application.repository.ICustomerController;
import com.api.apibackend.Customer.Application.useCase.CustomerUseCase;
import com.api.apibackend.Customer.Domain.handler.ClientNotFoundException;
import com.api.apibackend.Customer.Infra.util.JwtUtills;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@EnableCaching
@RestController
@RequestMapping("v1/auth")
public class CustomerController implements ICustomerController {
    @Autowired
    private CustomerUseCase customerUseCase;
    private JwtUtills jwtUtills;
    
    @PostMapping(path = "/entrar")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Login do usuariuo", description = "Informações de login do usuário")
    @Operation(summary = "Rota responsavel por efetuar o login de um usuario dentro da aplicação, desta forma disponibilazando que ele tenha acesso as outras paginas!")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
             return customerUseCase.executeLogin(loginRequest);
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
    }

    @PostMapping(path = "/valida/token")
    @Tag(name = "Valida token", description = "Verifica se o token do usuário está expirado")
    @Operation(summary = "Rota responsavel por validar o token do usuário para ver se já está expirado ou não")
    public ResponseEntity<String> validateToken(@RequestBody ValidateTokenRequest token) {
        if(token.getAccess_token().isEmpty()) {
            String valueToken = token.getAccess_token();
            jwtUtills.validateToken(valueToken);
            return ResponseEntity.status(HttpStatus.OK).body("Token valido");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalido!");
    }

    @PostMapping(path = "/registrar")
    @Tag(name = "Registra um usuário", description = "Registra o usuário no banco e gera um token de acesso para o mesmo")
    @Operation(summary = "Rota responsavel por registrar o usuario e gerar um token de autenticação para o mesmo!")
    public ResponseEntity<String> registerClientV2(@RequestBody RegistrationRequest registrationRequest) {
        try {
            ResponseEntity<String> response = customerUseCase.executeRegister(registrationRequest.getCustomerDTO(), registrationRequest.getCustomerAddressDTO());
            return response;
        } catch (RegistrationFailedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
