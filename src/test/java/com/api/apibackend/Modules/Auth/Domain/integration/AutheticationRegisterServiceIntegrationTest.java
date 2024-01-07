package com.api.apibackend.Modules.Auth.Domain.integration;

import com.api.apibackend.Modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Modules.Auth.Domain.authentication.AutheticationRegisterService;
import com.api.apibackend.Modules.Auth.Domain.service.AnonymizationService;
import com.api.apibackend.Modules.Auth.Domain.service.UserService;
import com.api.apibackend.Modules.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.Modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.Modules.Auth.Infra.validation.AutheticationValidationServiceHandler;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Modules.Customer.Domain.helpers.CustomerModelMapper;
import com.api.apibackend.Modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.Modules.Customer.Domain.service.CustomerService;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.Modules.CustomerAddress.Domain.helpers.CustomerAddressModelMapper;
import com.api.apibackend.Modules.CustomerAddress.infra.entity.AddressEntity;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class AutheticationRegisterServiceIntegrationTest {

    @Mock
    private AutheticationValidationServiceHandler autheticationValidationServiceHandler;

    @Mock
    private CustomerFilterService clientSearchService;

    @Mock
    private CustomerService clientServiceImp;

    @Mock
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;

    @Mock
    private CustomerAddressModelMapper customerAddressModelMapper;

    @Mock
    private CustomerModelMapper customerModelMapper;

    @Mock
    private AnonymizationService anonymizationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private UserService userService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AutheticationRegisterService autheticationRegisterService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private static final Logger logger = LoggerFactory.getLogger(AutheticationRegisterServiceIntegrationTest.class);

    @Test
    @DisplayName("TestRegisterReturnSuccess")
    public void testRegister_When_ValidData_ReturnSuccess() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setGender("masculino");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setCpf("12345678901");
        customerDTO.setIsAdmin(false);
        customerDTO.setPhone("123456789");
        customerDTO.setDateCreate(new Date());
        customerDTO.setBirthDate(new Date());
        customerDTO.setPassword("testPassword");

        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCep("12345678");
        customerAddressDTO.setNeighborhood("Neighborhood");
        customerAddressDTO.setRoad("Street");
        customerAddressDTO.setState("State");
        customerAddressDTO.setHousenumber("123");

        when(autheticationValidationServiceHandler.isValidEmail(any())).thenReturn(null);
        when(autheticationValidationServiceHandler.isValidPassword(any())).thenReturn(null);
        when(autheticationValidationServiceHandler.isValidCPF(any())).thenReturn(true);
        when(anonymizationService.encrypt(anyString())).thenReturn("encryptedValue");
        when(clientSearchService.searchClientByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1L);
        when(customerAddressModelMapper.toCustomerDTOFromAddressEntity(any()))
                .thenReturn(addressEntity);

        when(customerModelMapper.toCustomerDTOFromCustomerEntity(any()))
                .thenReturn(new CustomerEntity());

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setId(1L);
        when(userRepository.save(any()))
                .thenReturn(newUserEntity);

        CustomerEntity savedCustomerEntity = new CustomerEntity();
        savedCustomerEntity.setId(1L);
        when(customerRepository.save(any()))
                .thenReturn(savedCustomerEntity);

        when(generatedTokenAuthorizationService.generateToken(anyString(), any()))
                .thenReturn("mockedToken");

        ResponseEntity<ResponseMessageDTO> response = autheticationRegisterService.register(customerDTO, customerAddressDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuário registrado com sucesso!", response.getBody().getMessage());
        logger.info(() -> "Teste de integração passou com sucesso!");
    }
}

