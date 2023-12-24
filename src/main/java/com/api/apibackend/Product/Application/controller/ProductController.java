package com.api.apibackend.Product.Application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Product.Application.repository.IProductController;
import com.api.apibackend.Product.Application.service.GetAllProductService;
import com.api.apibackend.Product.Application.service.GetFirstProductService;
import com.api.apibackend.Product.Application.useCase.ProductUseCase;
import com.api.apibackend.Product.Domain.model.Product;


@RestController
@RequestMapping("v1/produto")
public class ProductController implements IProductController {
	private ProductUseCase productUseCase;
	private GetAllProductService getAllProductsService;
	private GetFirstProductService getFirstProduct;
		
	@Autowired
	public ProductController(
		ProductUseCase productUseCase,
		GetAllProductService getAllProductsService,
		GetFirstProductService getFirstProduct
	) {
		this.productUseCase = productUseCase;
		this.getAllProductsService = getAllProductsService;
		this.getFirstProduct = getFirstProduct;
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> productDTOList) {
		return productUseCase.productAdd(productDTOList);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<Product>> getAllProductsEndpoint() {
		List<Product> products = getAllProductsService.execute();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/limitado")
	public ResponseEntity<List<Product>> getFirstProducts() {
		List<Product> products = getFirstProduct.execute();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/pesquisar/{id}")
	public ResponseEntity<List<Product>> searchProduct(@PathVariable Long id) {
		List<Product> products = getAllProductsService.execute();

		List<Product> filteredProducts = products.stream()
                .filter(product -> product.getId().equals(id))
                .collect(Collectors.toList());

		return ResponseEntity.ok(filteredProducts);
	}
}
