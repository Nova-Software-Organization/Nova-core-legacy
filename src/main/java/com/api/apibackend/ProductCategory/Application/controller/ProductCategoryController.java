package com.api.apibackend.ProductCategory.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.ProductCategory.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.ProductCategory.Application.useCases.ProductCategoryCreatedUseCase;

@RestController
@RequestMapping("v1/produto/categoria")
public class ProductCategoryController {
    private ProductCategoryCreatedUseCase productCategoryCreatedUseCase;

    @Autowired
    public ProductCategoryController(ProductCategoryCreatedUseCase productCategoryCreatedUseCase) {
        this.productCategoryCreatedUseCase = productCategoryCreatedUseCase;
    }

    @PostMapping("/criar")
    public ResponseEntity<ResponseMessageDTO> create(ProductCategoryDTO productCategoryDTO) {
        return productCategoryCreatedUseCase.execute(productCategoryDTO);
    }
}