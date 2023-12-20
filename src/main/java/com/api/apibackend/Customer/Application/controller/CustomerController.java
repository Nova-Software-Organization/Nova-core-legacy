package com.api.apibackend.Customer.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Auth.Domain.service.exception.RegistrationFailedException;
import com.api.apibackend.Customer.Application.DTOs.registration.RegistrationRequest;
import com.api.apibackend.Customer.Application.repository.ICustomerController;
import com.api.apibackend.Customer.Application.useCase.CustomerUseCase;
import com.api.apibackend.Customer.Domain.handler.ClientNotFoundException;

@EnableCaching
@RestController
@RequestMapping("v1/auth")
public class CustomerController implements ICustomerController {
    
    @Autowired
    private CustomerUseCase customerUseCase;
    
    @PostMapping(path = "/entrar")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
             return customerUseCase.executeLoginUser(loginRequest);
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registerClientV2(@RequestBody RegistrationRequest registrationRequest) {
        try {
            ResponseEntity<String> response = customerUseCase.executeRegister(registrationRequest.getCustomerDTO(), registrationRequest.getCustomerAddressDTO());
            return response;
        } catch (RegistrationFailedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
