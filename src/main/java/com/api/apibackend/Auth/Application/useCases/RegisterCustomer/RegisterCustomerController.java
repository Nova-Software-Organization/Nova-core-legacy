/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Auth.Application.useCases.RegisterCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Auth.Domain.exception.RegistrationFailedException;
import com.api.apibackend.Customer.Application.DTOs.registration.RegistrationRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/auth")
public class RegisterCustomerController {
    private RegisterCustomerUseCase customerAuthRegisterUseCase;

    @Autowired
    public RegisterCustomerController(RegisterCustomerUseCase customerAuthRegisterUseCase) {
        this.customerAuthRegisterUseCase = customerAuthRegisterUseCase;
    }

    @PostMapping(path = "/registrar")
    @Tag(name = "Registra um usuário", description = "Registra o usuário no banco e gera um token de acesso para o mesmo")
    @Operation(summary = "Rota responsável por registrar o usuário e gerar um token de autenticação para o mesmo!")
    public ResponseEntity<ResponseMessageDTO> handle(@RequestBody RegistrationRequest registrationRequest) {
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
