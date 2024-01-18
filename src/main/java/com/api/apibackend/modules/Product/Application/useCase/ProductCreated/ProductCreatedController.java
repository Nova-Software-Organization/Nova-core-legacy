/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.ProductCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/produto")
public class ProductCreatedController {
    private ProductUseCaseCreated productUseCaseCreated;

    @Autowired
    public ProductCreatedController(ProductUseCaseCreated productUseCaseCreated) {
        this.productUseCaseCreated = productUseCaseCreated;
    }

    @PostMapping("/criar")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Adicionar Único Produto",
        description = "Adiciona um único produto ao banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> handle(@RequestBody ProductDTO productDTO) {
		return productUseCaseCreated.execute(productDTO);
	}
}
