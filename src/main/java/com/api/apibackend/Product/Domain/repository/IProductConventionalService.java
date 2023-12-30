package com.api.apibackend.Product.Domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;

public interface IProductConventionalService {
     ResponseEntity<ResponseMessageDTO> populationProduct(List<ProductDTO> productDTOList);
     ResponseEntity<ResponseMessageDTO> create(ProductDTO productDTO);
     ResponseEntity<ResponseMessageDTO> delete(Long productId);
     ResponseEntity<ResponseMessageDTO> deactivate(Long productId);
}
