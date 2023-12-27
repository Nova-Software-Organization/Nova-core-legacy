package com.api.apibackend.Product.Domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Domain.model.Product;

public interface IProductConventionalService {
     ResponseEntity<String> populationProduct(List<Product> productDTOList);
     ResponseEntity<String> create(ProductDTO productDTO);
     ResponseEntity<String> delete(Long productId);
     ResponseEntity<String> deactivate(Long productId);
}
