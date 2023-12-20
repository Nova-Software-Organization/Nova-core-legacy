package com.api.apibackend.Auth.Domain.authentication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Auth.Domain.service.UserDetailsService;
import com.api.apibackend.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Auth.Infra.entity.UserEntity;
import com.api.apibackend.Auth.Validation.AutheticationValidationServiceHandler;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Domain.event.CustomerCreated;
import com.api.apibackend.Customer.Domain.helpers.CustomerModelMapper;
import com.api.apibackend.Customer.Domain.service.CustomerSearchService;
import com.api.apibackend.Customer.Domain.service.CustomerServiceImp;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.Domain.helpers.CustomerAddressModelMapper;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

import jakarta.transaction.Transactional;

@Service
public class AutheticationRegister {

    private PasswordEncoder passwordEncoder;
    private CustomerServiceImp clientServiceImp;
    private UserDetailsService userService;
    private CustomerModelMapper customerModelMapper;
    private ApplicationEventPublisher eventPublisher;
    private CustomerSearchService clientSearchService;
    private CustomerAddressModelMapper customerAddressModelMapper;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;
    private AutheticationValidationServiceHandler autheticationValidationServiceHandler;
    
    @Autowired
    public AutheticationRegister(
        AutheticationValidationServiceHandler autheticationValidationServiceHandler,
        CustomerSearchService clientSearchService,
        CustomerServiceImp clientServiceImp,
        GeneratedTokenAuthorizationService generatedTokenAuthorizationService,
        CustomerAddressModelMapper customerAddressModelMapper,
        CustomerModelMapper customerModelMapper,
        ApplicationEventPublisher eventPublisher,
        UserDetailsService userService
    ) {
        this.autheticationValidationServiceHandler = autheticationValidationServiceHandler;
        this.clientSearchService = clientSearchService;
        this.clientServiceImp = clientServiceImp;
        this.passwordEncoder = passwordEncoder;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
        this.customerAddressModelMapper = customerAddressModelMapper;
        this.customerModelMapper = customerModelMapper;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
    }

    @Transactional
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
            
            UserEntity newUserEntity = new UserEntity();
            newUserEntity.setId(newCustomerModelMapperEntity.getId());
            newUserEntity.setUsername(customerDTO.getName());
            newUserEntity.setPassword(hashedPassword);
            newUserEntity.setCustomer(newCustomerModelMapperEntity);
            Set<CustomGrantedAuthority> userRoles = new HashSet<>();
            
            if (customerDTO.getIsAdmin()) {
                userRoles.add(CustomGrantedAuthority.ADMIN);
            } else {
                userRoles.add(CustomGrantedAuthority.USER);
            }
            
            newUserEntity.setRoles(customerDTO.getIsAdmin());
            CustomerEntity savedCustomer = clientServiceImp.createClient(newCustomerModelMapperEntity, newAddressEntityCustomer);
            String jwtToken = generatedTokenAuthorizationService.generateToken(newUserEntity.getUsername(), userRoles);
            UserEntity savedUserEntity = userService.createUser(newUserEntity, savedCustomer);
            
            CustomerCreated newCustomerCreated = new CustomerCreated(this, savedUserEntity.getId());
            eventPublisher.publishEvent(newCustomerCreated);

            return ResponseEntity.status(HttpStatus.CREATED).body("usuario registrado com sucesso! " + jwtToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar o cliente: " + e.getMessage());
        }
    }

    private Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles) {
        Set<CustomGrantedAuthority> authorities = new HashSet<>();
        if (roles != null) {
            for (CustomGrantedAuthority role : roles) {
                authorities.add(role);
            }
        }
        return authorities;
    }
}
