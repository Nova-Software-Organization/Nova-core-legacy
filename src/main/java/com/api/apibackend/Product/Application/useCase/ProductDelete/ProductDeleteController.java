package com.api.apibackend.Product.Application.useCase.ProductDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public class ProductDeleteController {
    private ProductDeleteUseCase productDeleteUseCase;

    @Autowired
    public ProductDeleteController(ProductDeleteUseCase productDeleteUseCase) {
        this.productDeleteUseCase = productDeleteUseCase;
    }

    @PostMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Deletar Produto",
        description = "Deleta um produto do banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> handle(@PathVariable Long id) {
		try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID do produto inválido", this.getClass().getName(), null));
            }

            return productDeleteUseCase.execute(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
	}
}
