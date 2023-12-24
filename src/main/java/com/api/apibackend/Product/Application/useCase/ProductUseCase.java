package com.api.apibackend.Product.Application.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Application.service.ProductAddService;
import com.api.apibackend.Product.Domain.model.Product;

@Service
public class ProductUseCase {

    @Autowired
    private ProductAddService productAddService;

    public ResponseEntity<List<Product>> productAdd(List<Product> productDTOList) {
        return productAddService.addProducts(productDTOList);
    }
}
