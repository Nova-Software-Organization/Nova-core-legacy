package com.api.apibackend.Product.Application.useCase.ProductPopulation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/produto")
public class ProductPopulationController {
    private ProductPopulationUseCase productPopulationUseCase;

    @Autowired
    public ProductPopulationController(ProductPopulationUseCase productPopulationUseCase) {
        this.productPopulationUseCase = productPopulationUseCase;
    }

    @PostMapping("/adicionar")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Adicionar Lista de Produtos",
        description = "Adiciona uma lista de produtos ao banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produtos adicionados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> handle(@RequestBody List<ProductDTO> productDTOList) {
		try {
            if (productDTOList == null || productDTOList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Lista de produtos vazia ou nula", this.getClass().getName(), null));
            }

            return productPopulationUseCase.execute(productDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), null));
        }
	}
}
