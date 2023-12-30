package com.api.apibackend.Product.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Product.Domain.service.ProductConventionalService;

@Service
public class ProductUseCaseCreated {
    private ProductConventionalService productConventionalService;

    @Autowired
    public ProductUseCaseCreated(ProductConventionalService productConventionalService) {
        this.productConventionalService = productConventionalService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(ProductDTO productDTO) {
        if (productDTO == null) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Ocorreu um erro ao tentar criar o produto formato de json inválido", this.getClass().getName(),null));
        }

        return productConventionalService.create(productDTO);
    }
}
