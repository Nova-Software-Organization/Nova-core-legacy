package com.api.apibackend.Product.Application.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Product.Domain.model.Product;

public interface IProductController {
	ResponseEntity<?> addProducts(@RequestBody List<Product> productDTOList);
	ResponseEntity<List<Product>> getAllProductsEndpoint();
	ResponseEntity<List<Product>> getFirstProducts();
	ResponseEntity<List<Product>> searchProduct(@PathVariable Long id);
}
