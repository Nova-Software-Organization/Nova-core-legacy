package com.api.apibackend.Modules.Auth.Domain.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.apibackend.Modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Modules.Auth.Domain.authentication.AutheticationRegisterService;
import com.api.apibackend.Modules.Auth.Domain.service.AnonymizationService;
import com.api.apibackend.Modules.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.Modules.Customer.Infra.persistence.repository.CustomerRepository;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class AutheticationRegisterServiceIntegrationTest {
    
    @Mock
    private AnonymizationService anonymizationService;

    @Mock
    private CustomerFilterService clientSearchService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AutheticationRegisterService autheticationRegisterService;

    private CustomerDTO customerDTO;
    private CustomerAddressDTO customerAddressDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setName("Joe");
        customerDTO.setLastName("Does");
        customerDTO.setGender("masculino");
        customerDTO.setEmail("teste@gmail.com");
        customerDTO.setCpf("70954417070");
        customerDTO.setIsAdmin(false);
        customerDTO.setPhone("11987482173");
        customerDTO.setDateCreate(new Date());
        customerDTO.setBirthDate(new Date());
        customerDTO.setPassword("teste123456789/");

        customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCep("07739800");
        customerAddressDTO.setNeighborhood("centro");
        customerAddressDTO.setRoad("Rua teste");
        customerAddressDTO.setState("são paulo");
        customerAddressDTO.setHousenumber("165");
    }

    void testRegister_When_CreateUser_ReturnToken() throws Exception {
        try (AutoCloseable autoCloseable = () -> {
            verify(anonymizationService, times(1)).encrypt("teste@gmail.com");
            verify(clientSearchService, times(1)).searchClientByEmail("teste@gmail.com");
        }) {
            when(anonymizationService.encrypt(anyString())).thenReturn("mockedEncryptedValue");
            when(clientSearchService.searchClientByEmail(anyString())).thenReturn(Optional.empty());

            ResponseEntity<ResponseMessageDTO> response = autheticationRegisterService.register(customerDTO, customerAddressDTO);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }
    }
}
