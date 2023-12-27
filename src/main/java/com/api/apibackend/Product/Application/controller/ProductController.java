package com.api.apibackend.Product.Application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Product.Application.repository.IProductController;
import com.api.apibackend.Product.Application.useCase.ProductAddUseCase;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.service.GetAllProductService;
import com.api.apibackend.Product.Domain.service.GetFirstProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("v1/produto")
public class ProductController implements IProductController {
	private ProductAddUseCase productUseCase;
	private GetAllProductService getAllProductsService;
	private GetFirstProductService getFirstProduct;
		
	@Autowired
	public ProductController(
		ProductAddUseCase productUseCase,
		GetAllProductService getAllProductsService,
		GetFirstProductService getFirstProduct
	) {
		this.productUseCase = productUseCase;
		this.getAllProductsService = getAllProductsService;
		this.getFirstProduct = getFirstProduct;
	}
	
	@PostMapping("/adicionar")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Adiciona produtos", description = "Adiciona produtos dentro banco de dados")
	@Operation(summary = "Rota responsavel por adicionar produtos no banco dados caso aja necessidade de adicionar pela própria aplicação central, Nova-core")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<String> addProducts(@RequestBody List<Product> productDTOList) {
		return productUseCase.productAdd(productDTOList);
	}

	@GetMapping("/todos")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Lista os produtos", description = "Lista todos os produtos dentro do banco de dados da respectiva loja")
	@Operation(summary = "Rota responsável por listar todos os produtos do banco dados caso aja necessidade")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> getAllProductsEndpoint() {
		List<Product> products = getAllProductsService.execute();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/limitado")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Lista os produtos de maneira limitada", description = "Lista os produtos dentro do banco de dados pegando os 12 primeiros")
	@Operation(summary = "Rota responsável por listar os 12 primeiros produtos do banco de dados")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> getFirstProducts() {
		List<Product> products = getFirstProduct.execute();
		return ResponseEntity.ok(products);
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
