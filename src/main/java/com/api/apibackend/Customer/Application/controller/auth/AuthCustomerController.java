package com.api.apibackend.Customer.Application.controller.auth;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Auth.Application.DTOs.AuthAccessToken;
import com.api.apibackend.Auth.Application.DTOs.LoginResponseDTO;
import com.api.apibackend.Auth.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Auth.Domain.exception.RegistrationFailedException;
import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Customer.Application.DTOs.registration.RegistrationRequest;
import com.api.apibackend.Customer.Application.repository.ICustomerController;
import com.api.apibackend.Customer.Application.useCase.auth.CustomerAuthLoginUseCase;
import com.api.apibackend.Customer.Application.useCase.auth.CustomerAuthRegisterUseCase;
import com.api.apibackend.Customer.Domain.exception.ClientNotFoundException;
import com.api.apibackend.Customer.Infra.util.JwtUtills;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@EnableCaching
@RestController
@RequestMapping("v1/auth")
public class AuthCustomerController implements ICustomerController {
    private CustomerAuthLoginUseCase customerLoginUseCase;
    private CustomerAuthRegisterUseCase customerAuthRegisterUseCase;
    private JwtUtills jwtUtills;

    @Autowired
    public AuthCustomerController(CustomerAuthLoginUseCase customerLoginUseCase,
            CustomerAuthRegisterUseCase customerAuthRegisterUseCase) {
        this.customerLoginUseCase = customerLoginUseCase;
        this.customerAuthRegisterUseCase = customerAuthRegisterUseCase;
    }

    @PostMapping(path = "/entrar")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Login do usuariuo", description = "Informações de login do usuário")
    @Operation(summary = "Rota responsavel por efetuar o login de um usuario dentro da aplicação, desta forma disponibilazando que ele tenha acesso as outras paginas!")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            return customerLoginUseCase.execute(loginRequest);
        } catch (ClientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponseDTO("Usuario não encontrado"));
        }
    }

    @PostMapping(path = "/valida/token")
    @Tag(name = "Valida token", description = "Verifica se o token do usuário está expirado")
    @Operation(summary = "Rota responsavel por validar o token do usuário para ver se já está expirado ou não")
    public ResponseEntity<String> validateToken(@RequestBody AuthAccessToken token) {
        if (token.getAccess_token().isEmpty()) {
            String valueToken = token.getAccess_token();
            jwtUtills.validateToken(valueToken);
            return ResponseEntity.status(HttpStatus.OK).body("Token valido");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalido!");
    }

    @PostMapping(path = "/registrar")
    @Tag(name = "Registra um usuário", description = "Registra o usuário no banco e gera um token de acesso para o mesmo")
    @Operation(summary = "Rota responsável por registrar o usuário e gerar um token de autenticação para o mesmo!")
    public ResponseEntity<ResponseMessageDTO> registerClientV2(@RequestBody RegistrationRequest registrationRequest) {
        try {
            return customerAuthRegisterUseCase.execute(registrationRequest.getCustomerDTO(),
                    registrationRequest.getCustomerAddressDTO());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(
                    new ResponseMessageDTO(null, this.getClass().getSimpleName(), ex.getMessage(), null));
        } catch (RegistrationFailedException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Erro ao processar a solicitação de registro: " + ex.getMessage(), null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Erro inesperado ao processar a solicitação de registro: " + ex.getMessage(), null));
        }
    }
}
