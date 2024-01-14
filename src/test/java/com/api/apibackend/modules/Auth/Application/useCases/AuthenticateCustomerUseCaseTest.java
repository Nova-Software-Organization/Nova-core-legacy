/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.apibackend.modules.Auth.Application.DTOs.request.LoginRequest;
import com.api.apibackend.modules.Auth.Application.DTOs.response.LoginResponseDTO;
import com.api.apibackend.modules.Auth.Application.useCases.AuthenticateCustomer.AuthenticateCustomerUseCase;
import com.api.apibackend.modules.Auth.Domain.authentication.AuthorizationLoginService;

@SpringBootTest
public class AuthenticateCustomerUseCaseTest {

    @Mock
    private AuthorizationLoginService authenticationLoginService;

    @InjectMocks
    private AuthenticateCustomerUseCase authenticateCustomerUseCase;

    @Test
    void testExecute_SuccessfulAuthentication() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("john.doe@example.com");
        loginRequest.setPassword("testPassword");

        ResponseEntity<LoginResponseDTO> successResponse = ResponseEntity.ok(new LoginResponseDTO("Token123"));
        when(authenticationLoginService.login(any())).thenReturn(successResponse);

        ResponseEntity<LoginResponseDTO> response = authenticateCustomerUseCase.execute(loginRequest);

        assertEquals(successResponse, response);
        assertNotNull(response.getBody());
        assertEquals("Token123", response.getBody().getToken());
    }

    @Test
    void testExecute_NullLoginRequest() {
        IllegalArgumentException expectedException = new IllegalArgumentException("Erro: dados de login não fornecidos");
        when(authenticationLoginService.login(null)).thenThrow(expectedException);

        ResponseEntity<LoginResponseDTO> actualResponse = authenticateCustomerUseCase.execute(null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertEquals("Erro ao processar a solicitação de login", actualResponse.getBody().getToken());

        verify(authenticationLoginService, times(1)).login(null);
    }
}
