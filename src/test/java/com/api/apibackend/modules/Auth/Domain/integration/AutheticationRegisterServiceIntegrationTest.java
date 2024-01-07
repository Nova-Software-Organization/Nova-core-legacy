package com.api.apibackend.modules.Auth.Domain.integration;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Domain.authentication.AutheticationRegisterService;
import com.api.apibackend.modules.Auth.Domain.service.AnonymizationService;
import com.api.apibackend.modules.Auth.Domain.service.UserService;
import com.api.apibackend.modules.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Auth.Infra.validation.AutheticationValidationServiceHandler;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.modules.Customer.Domain.helpers.CustomerModelMapper;
import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Domain.service.CustomerService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.modules.CustomerAddress.Domain.helpers.CustomerAddressModelMapper;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    @DisplayName("TestRegisterReturnSuccess")
    public void testRegister_When_ValidData_ReturnSuccess() {
        CustomerDTO customerDTO = createTestCustomerDTO();
        CustomerAddressDTO customerAddressDTO = createTestCustomerAddressDTO();
        setupMockBehaviors();
        ResponseEntity<ResponseMessageDTO> response = autheticationRegisterService.register(customerDTO, customerAddressDTO);
        assertResponse(response);
    }

    private CustomerDTO createTestCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setGender("masculino");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setCpf("12345678901");
        customerDTO.setIsAdmin(false);
        customerDTO.setPhone("123456789");
        customerDTO.setDateCreate(new Date());
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        customerDTO.setBirthDate(java.sql.Date.valueOf(birthDate));
        customerDTO.setPassword("testPassword");
        customerDTO.setIsAdmin(Boolean.FALSE);
        return customerDTO;
    }

    private CustomerAddressDTO createTestCustomerAddressDTO() {
        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCep("12345678");
        customerAddressDTO.setNeighborhood("Neighborhood");
        customerAddressDTO.setRoad("Street");
        customerAddressDTO.setState("State");
        customerAddressDTO.setHousenumber("123");
        return customerAddressDTO;
    }

    private void setupMockBehaviors() {
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
                .thenReturn(new CustomerEntity(createTestCustomerDTO()));

        CustomerEntity savedCustomerEntity = new CustomerEntity(createTestCustomerDTO());
        savedCustomerEntity.setId(1L);
        when(customerRepository.save(any()))
                .thenReturn(savedCustomerEntity);

        when(generatedTokenAuthorizationService.generateToken(anyString(), any()))
                .thenReturn("mockedToken");
    }

    private void assertResponse(ResponseEntity<ResponseMessageDTO> response) {
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseMessageDTO responseMessageDTO = response.getBody();
        assertNotNull(responseMessageDTO);
        assertEquals("Usuário registrado com sucesso!", responseMessageDTO.getMessage());

        String generatedToken = response.getBody().getAccessToken();
        assertNotNull(generatedToken);
        assertNotEquals("", generatedToken);

        assertEquals("Usuário registrado com sucesso!", response.getBody().getMessage());
    }
}
