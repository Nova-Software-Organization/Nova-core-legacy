package com.api.apibackend.modules.Product.Application.useCase.GetFirstProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class GetFirstProductController {
    private GetFirstProductUseCase getFirstProductUseCase;

    @Autowired
    public GetFirstProductController(GetFirstProductUseCase getFirstProductUseCase) {
        this.getFirstProductUseCase = getFirstProductUseCase;
    }

    @GetMapping("/limitado")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Listar os 12 Primeiros Produtos",
        description = "Lista os 12 primeiros produtos no banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> handle() {
		List<Product> products = getFirstProductUseCase.execute();
		return ResponseEntity.ok(products);
	}
}
