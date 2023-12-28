package com.api.apibackend.Product.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Domain.service.ProductConventionalService;

@Service
public class ProductDeleteUseCase {
    private ProductConventionalService productConventionalService;

    @Autowired
    public ProductDeleteUseCase(ProductConventionalService productConventionalService) {
        this.productConventionalService = productConventionalService;
    }

    public ResponseEntity<String> execute(Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de produto inválido");
        }
        
        return productConventionalService.delete(id);
    }
}
