/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.ProductDeactivate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/produto")
public class ProductDeactivateController {
    private ProductDeactivateUseCase productDeactivateUseCase;

    @Autowired
    public ProductDeactivateController(ProductDeactivateUseCase productDeactivateUseCase) {
        this.productDeactivateUseCase = productDeactivateUseCase;
    }

    @PostMapping("/desativar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Desativar Produto",
        description = "Desativa um produto, removendo-o das consultas."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto desativado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> handle(@PathVariable Long id) {
		try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID do produto inválido", this.getClass().getName(), null));
            }

            return productDeactivateUseCase.execute(id);
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
	}
}
