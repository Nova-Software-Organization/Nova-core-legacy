package com.api.apibackend.ProductCategory.Application.controller;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.ProductCategory.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.ProductCategory.Application.useCases.ProductCategoryCreatedUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/produto/categoria")
@Tag(name = "Categoria de Produto", description = "Operações relacionadas a categorias de produtos")
public class ProductCategoryController {
    private ProductCategoryCreatedUseCase productCategoryCreatedUseCase;

    @Autowired
    public ProductCategoryController(ProductCategoryCreatedUseCase productCategoryCreatedUseCase) {
        this.productCategoryCreatedUseCase = productCategoryCreatedUseCase;
    }

    @PostMapping("/criar")
    @Operation(
        summary = "Criar Categoria de Produto",
        description = "Cria uma nova categoria de produto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria de produto criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> create(ProductCategoryDTO productCategoryDTO) {
        return productCategoryCreatedUseCase.execute(productCategoryDTO);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(
        summary = "Atualizar Categoria de Produto",
        description = "Atualiza uma categoria de produto existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria de produto atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Categoria de produto não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> update(@PathVariable Long id, ProductCategoryDTO productCategoryDTO) {
        

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Categoria de produto atualizado com sucesso", this.getClass().getName(), null));
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(
        summary = "Excluir Categoria de Produto",
        description = "Exclui uma categoria de produto existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria de produto excluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Categoria de produto não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/desativar/{id}")
    @Operation(
        summary = "Desativar Categoria de Produto",
        description = "Desativa uma categoria de produto existente."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria de produto desativada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Categoria de produto não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> disable(@PathVariable Long id) {


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Categoria de produto desativada com sucesso", this.getClass().getName(), null));
    }
}