/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.ProductUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ProductUpdateController {
    private ProductUpdateUseCase productUpdateUseCase;

    @Autowired
    public ProductUpdateController(ProductUpdateUseCase productUpdateUseCase) {
        this.productUpdateUseCase = productUpdateUseCase;
    }

    @PostMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Atualizar Produto",
        description = "Atualiza um produto no banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> handle(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		try {
            if (productDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Produto inválido", this.getClass().getName(), null));
            }

            return productUpdateUseCase.execute(id, productDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
	}
}
