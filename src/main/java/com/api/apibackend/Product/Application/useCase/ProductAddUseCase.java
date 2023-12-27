package com.api.apibackend.Product.Application.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.service.ProductAddService;

@Service
public class ProductAddUseCase {
    private ProductAddService productAddService;
    
    @Autowired
    public ProductAddUseCase(ProductAddService productAddService) {
        this.productAddService = productAddService;
    }

    public ResponseEntity<String> productAdd(List<Product> productDTOList) {
        return productAddService.addProducts(productDTOList);
    }
}
