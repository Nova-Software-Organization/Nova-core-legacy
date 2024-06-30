/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.ProductCategory.Application.useCases.CategoryCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.modules.ProductCategory.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/produto/categoria")
@Tag(name = "Categoria de Produto", description = "Operações relacionadas a categorias de produtos")
public class ProductCategoryCreatedController {
    private ProductCategoryCreatedUseCase productCategoryCreatedUseCase;

    @Autowired
    public ProductCategoryCreatedController(ProductCategoryCreatedUseCase productCategoryCreatedUseCase) {
        this.productCategoryCreatedUseCase = productCategoryCreatedUseCase;
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar Categoria de Produto", description = "Cria uma nova categoria de produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria de produto criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> handle(ProductCategoryDTO productCategoryDTO) {
        return productCategoryCreatedUseCase.execute(productCategoryDTO);
    }
}
