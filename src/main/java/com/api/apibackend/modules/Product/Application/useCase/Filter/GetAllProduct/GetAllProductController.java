/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.GetAllProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Product.Domain.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("v1/produto")
public class GetAllProductController {
    private GetAllProductUseCase getAllProductUseCase;

    @Autowired
    public GetAllProductController(GetAllProductUseCase getAllProductUseCase) {
        this.getAllProductUseCase = getAllProductUseCase;
    }

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Listar Todos os Produtos",
        description = "Lista todos os produtos no banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> handle() {
		List<Product> products = getAllProductUseCase.execute();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
