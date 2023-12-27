package com.api.apibackend.Product.Application.controller.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.service.GetAllProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

public class ProductFilterController {
    private GetAllProductService getAllProductsService;

    @Autowired
    public ProductFilterController(GetAllProductService getAllProductsService) {
        this.getAllProductsService = getAllProductsService;
    }

    @GetMapping("/pesquisar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Busca por um produto pelo ID", description = "Busca pelo produto pelo ID passado na requisição")
	@Operation(summary = "Rota responsável por buscar produtos no banco de dados pelo ID")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> searchProduct(@PathVariable Long id) {
		List<Product> products = getAllProductsService.execute();
		List<Product> filteredProducts = products.stream()
                .filter(product -> product.getId().equals(id))
                .collect(Collectors.toList());

		return ResponseEntity.ok(filteredProducts);
	}
}
