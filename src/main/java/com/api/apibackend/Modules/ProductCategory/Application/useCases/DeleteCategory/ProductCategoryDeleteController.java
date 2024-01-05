/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.ProductCategory.Application.useCases.DeleteCategory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/categoria")
public class ProductCategoryDeleteController {

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir Categoria de Produto", description = "Exclui uma categoria de produto existente.")
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
}