/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ProductCategory.Application.useCases.Disabled;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.ProductCategory.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/categoria")
public class ProductCategoryDisabledController {

    @PatchMapping("/desativar/{id}")
    @Operation(summary = "Desativar Categoria de Produto", description = "Desativa uma categoria de produto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria de produto desativada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Categoria de produto não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ResponseMessageDTO> disable(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessageDTO("Categoria de produto desativada com sucesso", this.getClass().getName(), null));
    }
}
