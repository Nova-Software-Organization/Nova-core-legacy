package com.api.apibackend.ProductCategory.Application.useCases;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.ProductCategory.Domain.service.ProductCategoryConventionalService;

@Service
public class ProductCategoryCreatedUseCase {
    private ProductCategoryConventionalService productCategoryCreatedService;

    public ResponseEntity<String> execute(ProductCategoryDTO productCategoryDTO) {
        return productCategoryCreatedService.create(productCategoryDTO);
    }
}
