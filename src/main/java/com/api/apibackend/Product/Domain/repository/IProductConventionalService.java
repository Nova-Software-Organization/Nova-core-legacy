package com.api.apibackend.Product.Domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;

public interface IProductConventionalService {
     ResponseEntity<String> populationProduct(List<ProductDTO> productDTOList);
     ResponseEntity<String> create(ProductDTO productDTO);
     ResponseEntity<String> delete(Long productId);
     ResponseEntity<String> deactivate(Long productId);
}
