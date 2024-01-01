/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Customer.Application.useCase.CustomerUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Domain.exception.ErrorEmptyCustomer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/cliente")
public class CustomerUpdateController {
    private CustomerUpdateUseCase customerUpdateUseCase;

    @Autowired
    public CustomerUpdateController(CustomerUpdateUseCase customerUpdateUseCase) {
        this.customerUpdateUseCase = customerUpdateUseCase;
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
    public ResponseEntity<ResponseMessageDTO> handle(
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
}