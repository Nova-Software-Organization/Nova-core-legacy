package com.api.apibackend.Customer.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.authentication.AutheticationRegister;
import com.api.apibackend.Auth.Domain.authentication.AuthorizationLogin;
import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Customer.Application.DTOs.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.CustomerDTO;

@Service
public class CustomerUseCase {
    
    private AuthorizationLogin authenticationManager;
    private AutheticationRegister authorizationRegister;

    @Autowired
    public CustomerUseCase (AuthorizationLogin authenticationManagerService, AutheticationRegister authorizationRegister) {
        this.authenticationManager = authenticationManagerService;
        this.authorizationRegister = authorizationRegister;
    }

    public ResponseEntity<String> executeLoginUser(LoginRequest loginRequest) throws Exception {
        if (loginRequest != null) {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationManager.login(loginRequest));
        }

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: dados não existem");
    }

    public ResponseEntity<String> executeRegister(CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
        if (customerDTO != null && customerAddressDTO != null) {
            return authorizationRegister.registerUserWithSeparateData(customerDTO, customerAddressDTO);
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: dados não existem");
    }
}
