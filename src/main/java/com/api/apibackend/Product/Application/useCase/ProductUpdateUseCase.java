package com.api.apibackend.Product.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Domain.service.ProductConventionalService;

@Service
public class ProductUpdateUseCase {
    private ProductConventionalService productConventionalService;

    @Autowired
    public ProductUpdateUseCase(ProductConventionalService productConventionalService) {
        this.productConventionalService = productConventionalService;
    }

    public ResponseEntity<String> execute(Long id, ProductDTO productDTO) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de produto inválido");
        }

        if (productDTO == null) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto invalido!");
        }

        return productConventionalService.update(id, productDTO);
    }
}
