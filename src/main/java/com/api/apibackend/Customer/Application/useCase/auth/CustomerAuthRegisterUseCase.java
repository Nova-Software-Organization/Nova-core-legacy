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

import com.api.apibackend.Auth.Application.DTOs.ResponseMessageDTO;
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
    
    public ResponseEntity<ResponseMessageDTO> execute(CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
        try {
            if (customerDTO == null || customerAddressDTO == null) {
                throw new IllegalArgumentException("Erro: dados de cliente ou endereço não fornecidos");
            }
    
            ResponseEntity<ResponseMessageDTO> registrationResponse = authorizationRegister.registerUserWithSeparateData(customerDTO, customerAddressDTO);
    
            if (registrationResponse.getStatusCode() == HttpStatus.CREATED) {
                return registrationResponse;
            } else {
                return ResponseEntity.badRequest().body(
                        new ResponseMessageDTO(registrationResponse.getBody().getMessage(),
                                this.getClass().getSimpleName(), registrationResponse.getBody().getErrorMessage(), null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Erro ao processar a solicitação de registro: " + e.getMessage(), null));
        }
    }
}
