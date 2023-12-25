package com.api.apibackend.Customer.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.authentication.AutheticationRegister;
import com.api.apibackend.Auth.Domain.authentication.AuthorizationLogin;
import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;

@Service
public class CustomerUseCase {
    
    private final AuthorizationLogin authenticationLogin;
    private final AutheticationRegister authorizationRegister;

    @Autowired
    public CustomerUseCase(AuthorizationLogin authenticationLogin, AutheticationRegister authorizationRegister) {
        this.authenticationLogin = authenticationLogin;
        this.authorizationRegister = authorizationRegister;
    }

    public ResponseEntity<String> executeLogin(LoginRequest loginRequest) {
        try {
            if (loginRequest == null) {
                throw new IllegalArgumentException("Erro: dados de login não fornecidos");
            }
            return authenticationLogin.login(loginRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação de login");
        }
    }

    public ResponseEntity<String> executeRegister(CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
        try {
            if (customerDTO == null || customerAddressDTO == null) {
                throw new IllegalArgumentException("Erro: dados de cliente ou endereço não fornecidos");
            }
            return authorizationRegister.registerUserWithSeparateData(customerDTO, customerAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação de registro");
        }
    }
}
