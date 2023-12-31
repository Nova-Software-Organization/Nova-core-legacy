package com.api.apibackend.Customer.Application.controller;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Application.useCase.CustomerDeactivateUseCase;
import com.api.apibackend.Customer.Application.useCase.CustomerDeleteUseCase;
import com.api.apibackend.Customer.Application.useCase.CustomerUpdateUseCase;
import com.api.apibackend.Customer.Domain.exception.ErrorEmptyCustomer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/cliente")
@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
public class CustomerController {
    private CustomerUpdateUseCase customerUpdateUseCase;
    private CustomerDeleteUseCase customerDeleteUseCase;
    private CustomerDeactivateUseCase customerDeactivateUseCase;

    @Autowired
    public CustomerController(
            CustomerUpdateUseCase customerUpdateUseCase,
            CustomerDeleteUseCase customerDeleteUseCase,
            CustomerDeactivateUseCase customerDeactivateUseCase) {
        this.customerUpdateUseCase = customerUpdateUseCase;
        this.customerDeleteUseCase = customerDeleteUseCase;
        this.customerDeactivateUseCase = customerDeactivateUseCase;
    }

    @PutMapping(path = "/atualizar/{id}")
    @Operation(summary = "Atualizar Cliente", description = "Atualiza os dados de um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDTO customerDTO,
            CustomerAddressDTO customerAddressDTO) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("ID do cliente inválido", this.getClass().getName(), null));
            }

            if (customerDTO == null) {
                throw new ErrorEmptyCustomer("Erro: dados de cliente não fornecidos!");
            }

            return customerUpdateUseCase.execute(id, customerDTO, customerAddressDTO);
        } catch (ErrorEmptyCustomer e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessageDTO("Erro", this.getClass().getName(), e.getMessage()));
        }
    }

    @DeleteMapping(path = "/deletar/{id}")
    @Operation(summary = "Deletar Cliente", description = "Deleta um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> deleteCustomer(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("ID do cliente inválido", this.getClass().getName(), null));
            }

            return customerDeleteUseCase.execute(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessageDTO("Erro", this.getClass().getName(), e.getMessage()));
        }
    }
}
