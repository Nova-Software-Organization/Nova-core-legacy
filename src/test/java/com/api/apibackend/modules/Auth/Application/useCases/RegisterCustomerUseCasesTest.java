package com.api.apibackend.modules.Auth.Application.useCases;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Application.useCases.RegisterCustomer.RegisterCustomerUseCase;
import com.api.apibackend.modules.Auth.Domain.authentication.AutheticationRegisterService;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.modules.Auth.Domain.exception.RegistrationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class RegisterCustomerUseCaseTest {

    @Mock
    private AutheticationRegisterService autheticationRegisterService;

    @InjectMocks
    private RegisterCustomerUseCase registerCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExecute_SuccessfulRegistration() {
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

        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCep("12345678");
        customerAddressDTO.setNeighborhood("Neighborhood");
        customerAddressDTO.setRoad("Street");
        customerAddressDTO.setState("State");
        customerAddressDTO.setHousenumber("123");

        ResponseEntity<ResponseMessageDTO> expectedResponse = ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessageDTO("Usuário registrado com sucesso!", RegisterCustomerUseCase.class.getSimpleName(), null, "jwtToken"));

        when(autheticationRegisterService.register(any(), any())).thenReturn(expectedResponse);
        ResponseEntity<ResponseMessageDTO> actualResponse = registerCustomerUseCase.execute(customerDTO, customerAddressDTO);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("TestExecuteRegistrationFailure")
    void testExecute_RegistrationFailure() {
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

        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCep("12345678");
        customerAddressDTO.setNeighborhood("Neighborhood");
        customerAddressDTO.setRoad("Street");
        customerAddressDTO.setState("State");
        customerAddressDTO.setHousenumber("123");

        when(autheticationRegisterService.register(any(), any())).thenThrow(new RegistrationFailedException("RegistrationFailedException"));
        ResponseEntity<ResponseMessageDTO> actualResponse = registerCustomerUseCase.execute(customerDTO, customerAddressDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertNotNull(actualResponse.getBody());
    }

    @Test
    @DisplayName("TestNullCustomer")
    void testExecute_NullCustomerDTO() {
        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setCep("12345678");
        customerAddressDTO.setNeighborhood("Neighborhood");
        customerAddressDTO.setRoad("Street");
        customerAddressDTO.setState("State");
        customerAddressDTO.setHousenumber("123");

        ResponseEntity<ResponseMessageDTO> actualResponse = registerCustomerUseCase.execute(null, customerAddressDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertNotNull(actualResponse.getBody());
        assertNull(actualResponse.getBody().getMessage());
    }

    @Test
    @DisplayName("TestExecuteNullCustomerAddress")
    void testExecute_NullCustomerAddressDTO() {
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

        ResponseEntity<ResponseMessageDTO> actualResponse = registerCustomerUseCase.execute(customerDTO, null);

        assertEquals(HttpStatus.BAD_REQUEST, actualResponse.getStatusCode());
        assertNotNull(actualResponse.getBody());
    }
}

