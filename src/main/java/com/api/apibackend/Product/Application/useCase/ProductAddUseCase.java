package com.api.apibackend.Product.Application.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Domain.service.ProductConventionalService;

@Service
public class ProductAddUseCase {
    private ProductConventionalService productConventionalService;
    
    @Autowired
    public ProductAddUseCase(ProductConventionalService productConventionalService) {
        this.productConventionalService = productConventionalService;
    }

    public ResponseEntity<String> execute(List<ProductDTO> productDTOList) {
        if (productDTOList == null || productDTOList.isEmpty()) {
            return ResponseEntity.badRequest().body("A lista de produtos está vazia ou nula.");
        }

        for (ProductDTO product : productDTOList) {
            if (product.getName() == null || product.getName().isEmpty()) {
                return ResponseEntity.badRequest().body("O nome do produto é obrigatório.");
            }

            if (product.getPriceEntity().getPrice().intValue() <= 0) {
                return ResponseEntity.badRequest().body("O preço do produto deve ser um número positivo.");
            }
        }

        return productConventionalService.populationProduct(productDTOList);
    }
}
