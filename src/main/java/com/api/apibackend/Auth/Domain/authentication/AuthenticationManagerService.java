package com.api.apibackend.Auth.Domain.authentication;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Auth.Validation.AutheticationValidationServiceHandler;
import com.api.apibackend.Customer.Domain.service.CustomerSearchService;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

import jakarta.validation.Valid;

@Service
public class AuthenticationManagerService {

    private final AutheticationValidationServiceHandler autheticationValidationServiceHandler;
    private final CustomerSearchService clientSearchService;

    @Autowired
    public AuthenticationManagerService(
        AutheticationValidationServiceHandler autheticationValidationServiceHandler,
        CustomerSearchService clientSearchService
    ) {
        this.autheticationValidationServiceHandler = autheticationValidationServiceHandler;
        this.clientSearchService = clientSearchService;
    }

    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            String emailValidation = autheticationValidationServiceHandler.isValidEmail(email);

            if (emailValidation != null) {
                 return ResponseEntity.status(HttpStatus.FOUND).body("Usuario não pode ser autenticado!");
            }

            CustomerEntity customerEntity = clientSearchService.searchClientByEmail(email);
            if (customerEntity != null && BCrypt.checkpw(password, customerEntity.getPassword())) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário autenticado");
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        }
    }
}
