package com.api.apibackend.Product.Application.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Domain.model.Product;

public interface IProductController {
	ResponseEntity<?> populationCreationProduct(@RequestBody List<ProductDTO> productDTOList);
	ResponseEntity<List<Product>> getFirstProducts();
	public ResponseEntity<List<Product>> getAllProductsEndpoint();
}
