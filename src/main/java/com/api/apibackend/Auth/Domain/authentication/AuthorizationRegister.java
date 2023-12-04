package com.api.apibackend.Auth.Domain.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Auth.Infra.entity.UserEntity;
import com.api.apibackend.Auth.Validation.AutheticationValidationServiceHandler;
import com.api.apibackend.Customer.Application.DTOs.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.CustomerDTO;
import com.api.apibackend.Customer.Domain.event.CustomerCreated;
import com.api.apibackend.Customer.Domain.helpers.CustomerModelMapper;
import com.api.apibackend.Customer.Domain.service.CustomerSearchService;
import com.api.apibackend.Customer.Domain.service.CustomerServiceImp;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.Domain.helpers.CustomerAddressModelMapper;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

@Service
public class AuthorizationRegister {

    private AutheticationValidationServiceHandler autheticationValidationServiceHandler;
    private CustomerSearchService clientSearchService;
    private CustomerServiceImp clientServiceImp;
    private PasswordEncoder passwordEncoder;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;
    private CustomerModelMapper customerModelMapper;
    private CustomerAddressModelMapper customerAddressModelMapper;
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    public AuthorizationRegister(
        AutheticationValidationServiceHandler autheticationValidationServiceHandler,
        CustomerSearchService clientSearchService,
        CustomerServiceImp clientServiceImp,
        PasswordEncoder passwordEncoder,
        GeneratedTokenAuthorizationService generatedTokenAuthorizationService,
        CustomerAddressModelMapper customerAddressModelMapper,
        CustomerModelMapper customerModelMapper,
        ApplicationEventPublisher eventPublisher
    ) {
        this.autheticationValidationServiceHandler = autheticationValidationServiceHandler;
        this.clientSearchService = clientSearchService;
        this.clientServiceImp = clientServiceImp;
        this.passwordEncoder = passwordEncoder;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
        this.customerAddressModelMapper = customerAddressModelMapper;
        this.customerModelMapper = customerModelMapper;
        this.eventPublisher = eventPublisher;
    }

    public ResponseEntity<String> registerUserWithSeparateData(CustomerDTO customerDTO, CustomerAddressDTO CustomerAddressDTO) {
        try {
            CustomerEntity existingClient = clientSearchService.searchClientByEmail(customerDTO.getEmail());

            if (existingClient != null) {
                return ResponseEntity.badRequest().body("E-mail já está em uso.");
            }

            if (!autheticationValidationServiceHandler.isValidCPF(customerDTO.getCpf())) {
                return ResponseEntity.badRequest().body("CPF inválido.");
            }

            String emailValidation = autheticationValidationServiceHandler.isValidEmail(customerDTO.getEmail());
            String passwordValidation = autheticationValidationServiceHandler.isValidPassword(customerDTO.getPassword());

            if (emailValidation != null || passwordValidation != null) {
                return ResponseEntity.badRequest()
                        .body("Erro de validação: " + emailValidation + ", " + passwordValidation + " inválidos");
            }
            
            String plainPassword = customerDTO.getPassword();
            String hashedPassword = passwordEncoder.encode(plainPassword);

            CustomerEntity newCustomerModelMapperEntity = customerModelMapper.toCustomerDTOFromCustomerEntity(customerDTO);
            newCustomerModelMapperEntity.setPassword(hashedPassword);
            AddressEntity newAddressEntityCustomer = customerAddressModelMapper.toCustomerDTOFromAddressEntity(CustomerAddressDTO);
            
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(customerDTO.getName());
            userEntity.setPassword(hashedPassword);
            userEntity.setRoles(customerDTO.getIsAdmin());
            userEntity.setCustomer(newCustomerModelMapperEntity);

            CustomerEntity savedCustomer = clientServiceImp.createClient(newCustomerModelMapperEntity, newAddressEntityCustomer);
            // String jwtToken = generatedTokenAuthorizationService.generateToken(userEntity.getUsername());
            
            CustomerCreated newCustomerCreated = new CustomerCreated(this, newCustomerModelMapperEntity.getId());
            eventPublisher.publishEvent(newCustomerCreated);

            return ResponseEntity.status(HttpStatus.CREATED).body("usuario registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar o cliente: " + e.getMessage());
        }
    }
}
