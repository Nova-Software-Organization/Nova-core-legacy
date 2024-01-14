/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.authentication;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.modules.Auth.Domain.event.AuthCreated;
import com.api.apibackend.modules.Auth.Domain.repository.IAutheticationRegister;
import com.api.apibackend.modules.Auth.Domain.service.cryptography.AnonymizationService;
import com.api.apibackend.modules.Auth.Domain.service.user.UserService;
import com.api.apibackend.modules.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Auth.Infra.validation.AuthenticationValidationServiceHandler;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.modules.Customer.Domain.event.CustomerCreated;
import com.api.apibackend.modules.Customer.Domain.helpers.CustomerModelMapper;
import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Domain.service.CustomerService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.modules.CustomerAddress.Domain.helpers.CustomerAddressModelMapper;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AutheticationRegisterService implements IAutheticationRegister {
    private PasswordEncoder passwordEncoder;
    private CustomerService clientServiceImp;
    private UserService userService;
    private CustomerModelMapper customerModelMapper;
    private ApplicationEventPublisher eventPublisher;
    private CustomerFilterService clientSearchService;
    private CustomerAddressModelMapper customerAddressModelMapper;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;
    private AuthenticationValidationServiceHandler autheticationValidationServiceHandler;
    private AnonymizationService anonymizationService;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    @Autowired
    public AutheticationRegisterService(
            AuthenticationValidationServiceHandler autheticationValidationServiceHandler,
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
    public ResponseEntity<ResponseMessageDTO> register(
            CustomerDTO customerDTO,
            CustomerAddressDTO customerAddressDTO
    ) {
        try {
            List<String> emailValidation = autheticationValidationServiceHandler.isValidEmail(customerDTO.getEmail());
            String passwordValidation = autheticationValidationServiceHandler
                    .isValidPassword(customerDTO.getPassword());

            if (emailValidation != null || passwordValidation != null) {
                String errorMessage = "Erro de validação: ";
                if (emailValidation != null) {
                    errorMessage += String.join(", ", emailValidation);
                }

                if (passwordValidation != null) {
                    if (passwordValidation != null) {
                        errorMessage += ", ";
                    }
                    errorMessage += String.join(", ", passwordValidation);
                }

                return ResponseEntity.badRequest().body(new ResponseMessageDTO(
                        errorMessage, this.getClass().getSimpleName(), null, null));
            }

            Optional<CustomerEntity> existingCustomer = clientSearchService.searchClientByEmail(customerDTO.getEmail());
            if (existingCustomer.isPresent()) {
                log.warn("usuário já existe {}");
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("E-mail já está em uso.", this.getClass().getSimpleName(), null,
                                null));
            }

            if (!autheticationValidationServiceHandler.isValidCPF(customerDTO.getCpf())) {
                log.warn("erro de válidação no cpf do usuário {}");
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
            
            //Create event for sending email
            AuthCreated user = new AuthCreated(this, savedClient.getId());
            eventPublisher.publishEvent(user);

            log.info("usuario criado com sucesso {}");
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseMessageDTO("Usuário registrado com sucesso!",
                            this.getClass().getSimpleName(), null, jwtToken));
        } catch (Exception e) {
            log.error("Ocorreu um erro ao processar a requisição!", e.getMessage());
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
