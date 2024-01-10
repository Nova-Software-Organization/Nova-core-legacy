/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Application.useCase.CustomerDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Customer.Application.DTOs.response.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/cliente")
public class CustomerDeleteController {
    private CustomerDeleteUseCase customerDeleteUseCase;

    @Autowired
    public CustomerDeleteController(CustomerDeleteUseCase customerDeleteUseCase) {
        this.customerDeleteUseCase = customerDeleteUseCase;
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
