/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Auth.Domain.authentication;

import java.util.Date;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Auth.Domain.repository.IAutheticationRegister;
import com.api.apibackend.Auth.Domain.service.AnonymizationService;
import com.api.apibackend.Auth.Domain.service.UserService;
import com.api.apibackend.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.Auth.Infra.validation.AutheticationValidationServiceHandler;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Domain.event.CustomerCreated;
import com.api.apibackend.Customer.Domain.helpers.CustomerModelMapper;
import com.api.apibackend.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.Customer.Domain.service.CustomerService;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.CustomerAddress.Domain.helpers.CustomerAddressModelMapper;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

import jakarta.transaction.Transactional;

@Service
public class AutheticationRegister implements IAutheticationRegister {
    private PasswordEncoder passwordEncoder;
    private CustomerService clientServiceImp;
    private UserService userService;
    private CustomerModelMapper customerModelMapper;
    private ApplicationEventPublisher eventPublisher;
    private CustomerFilterService clientSearchService;
    private CustomerAddressModelMapper customerAddressModelMapper;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;
    private AutheticationValidationServiceHandler autheticationValidationServiceHandler;
    private AnonymizationService anonymizationService;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    @Autowired
    public AutheticationRegister(
            AutheticationValidationServiceHandler autheticationValidationServiceHandler,
            CustomerFilterService clientSearchService,
            CustomerService clientServiceImp,
            GeneratedTokenAuthorizationService generatedTokenAuthorizationService,
            CustomerAddressModelMapper customerAddressModelMapper,
            CustomerModelMapper customerModelMapper,
            ApplicationEventPublisher eventPublisher,
            UserService userService,
            PasswordEncoder passwordEncoder,
            AnonymizationService anonymizationService,
            CustomerRepository customerRepository,
            UserRepository userRepository) {
        this.autheticationValidationServiceHandler = autheticationValidationServiceHandler;
        this.clientSearchService = clientSearchService;
        this.clientServiceImp = clientServiceImp;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
        this.customerAddressModelMapper = customerAddressModelMapper;
        this.customerModelMapper = customerModelMapper;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.anonymizationService = anonymizationService;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> registerUserWithSeparateData(CustomerDTO customerDTO,
            CustomerAddressDTO customerAddressDTO) {
        try {
            String emailValidation = autheticationValidationServiceHandler.isValidEmail(customerDTO.getEmail());
            String passwordValidation = autheticationValidationServiceHandler
                    .isValidPassword(customerDTO.getPassword());

            if (emailValidation != null || passwordValidation != null) {
                return ResponseEntity.badRequest().body(new ResponseMessageDTO(
                        "Erro de validação: " + emailValidation + ", " + passwordValidation + " inválidos",
                        this.getClass().getSimpleName(), null, null));
            }

            Optional<CustomerEntity> existingCustomer = clientSearchService.searchClientByEmail(customerDTO.getEmail());
            if (existingCustomer.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("E-mail já está em uso.", this.getClass().getSimpleName(), null,
                                null));
            }

            if (!autheticationValidationServiceHandler.isValidCPF(customerDTO.getCpf())) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("CPF inválido.", this.getClass().getSimpleName(), null, null));
            }

            String emailAnonymization = anonymizationService.encrypt(customerDTO.getEmail());
            String cpfAnonymization = anonymizationService.encrypt(customerDTO.getCpf());

            String hashedPassword = passwordEncoder.encode(customerDTO.getPassword());

            CustomerEntity newCustomerEntity = createNewCustomerEntity(customerDTO, emailAnonymization,
                    cpfAnonymization);
            UserEntity newUserEntity = createUserEntity(customerDTO, hashedPassword, newCustomerEntity,
                    emailAnonymization);
            userRepository.save(newUserEntity);

            AddressEntity newAddressEntityCustomer = createNewAddressEntityCustomer(customerAddressDTO);
            newCustomerEntity.setAddress(newAddressEntityCustomer);
            newCustomerEntity.setUser(newUserEntity);

            CustomerEntity savedClient = customerRepository.save(newCustomerEntity);
            Set<CustomGrantedAuthority> userRoles = determineUserRoles(customerDTO);

            String jwtToken = generatedTokenAuthorizationService.generateToken(newUserEntity.getUsername(), userRoles);
            newCustomerEntity.setUser(newUserEntity);

            publishCustomerCreatedEvent(savedClient.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseMessageDTO("Usuário registrado com sucesso!",
                            this.getClass().getSimpleName(), null, jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Erro ao registrar o cliente: " + e.getMessage(), null));
        }
    }

    private CustomerEntity createNewCustomerEntity(CustomerDTO customerDTO,
            String emailAnonymization,
            String cpfAnonymization) {
        CustomerEntity newCustomerModelMapperEntity = customerModelMapper.toCustomerDTOFromCustomerEntity(customerDTO);
        newCustomerModelMapperEntity.setDateCreate(new Date());
        newCustomerModelMapperEntity.setCpf(cpfAnonymization);
        newCustomerModelMapperEntity.setEmail(emailAnonymization);
        return newCustomerModelMapperEntity;
    }

    private AddressEntity createNewAddressEntityCustomer(CustomerAddressDTO customerAddressDTO) {
        AddressEntity newAddressEntityCustomer = customerAddressModelMapper
                .toCustomerDTOFromAddressEntity(customerAddressDTO);
        newAddressEntityCustomer.setCep(anonymizationService.anonymizeCep(customerAddressDTO.getCep()));
        return newAddressEntityCustomer;
    }

    private UserEntity createUserEntity(CustomerDTO customerDTO, String hashedPassword,
            CustomerEntity newCustomerEntity, String emailAnonymization) {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(customerDTO.getName());
        newUserEntity.setPassword(hashedPassword);
        newUserEntity.setEmail(emailAnonymization);
        newUserEntity.setCustomer(newCustomerEntity);
        newUserEntity.setRoles(determineUserRoles(customerDTO));
        return newUserEntity;
    }

    private Set<CustomGrantedAuthority> determineUserRoles(CustomerDTO customerDTO) {
        Set<CustomGrantedAuthority> userRoles = new HashSet<>();
        userRoles.add(customerDTO.getIsAdmin() ? CustomGrantedAuthority.ADMIN : CustomGrantedAuthority.USER);
        return userRoles;
    }

    private void publishCustomerCreatedEvent(Long userId) {
        CustomerCreated newCustomerCreated = new CustomerCreated(this, userId);
        eventPublisher.publishEvent(newCustomerCreated);
    }

    public Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles) {
        Set<CustomGrantedAuthority> authorities = new HashSet<>();
        if (roles != null) {
            for (CustomGrantedAuthority role : roles) {
                authorities.add(role);
            }
        }
        return authorities;
    }
}
