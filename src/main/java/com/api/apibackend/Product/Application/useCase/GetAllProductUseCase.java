package com.api.apibackend.Product.Application.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.service.GetAllProductService;

@Service
public class GetAllProductUseCase {
    private GetAllProductService getAllProductService;

    @Autowired
    public GetAllProductUseCase(GetAllProductService getAllProductService) {
        this.getAllProductService = getAllProductService;
    }

    public List<Product> execute() {
        return getAllProductService.listProducts();
    }
}