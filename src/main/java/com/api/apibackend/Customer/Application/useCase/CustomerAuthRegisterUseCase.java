package com.api.apibackend.Customer.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.authentication.AutheticationRegister;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;

@Service
public class CustomerAuthRegisterUseCase {
    private AutheticationRegister authorizationRegister;

    @Autowired
    public CustomerAuthRegisterUseCase(AutheticationRegister authorizationRegister) {
        this.authorizationRegister = authorizationRegister;
    }

    public ResponseEntity<String> execute(CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
        try {
            if (customerDTO == null || customerAddressDTO == null) {
                throw new IllegalArgumentException("Erro: dados de cliente ou endereço não fornecidos");
            }
            return authorizationRegister.registerUserWithSeparateData(customerDTO, customerAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a solicitação de registro");
        }
    }
}
