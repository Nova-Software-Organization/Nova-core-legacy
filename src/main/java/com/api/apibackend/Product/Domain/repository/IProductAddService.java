package com.api.apibackend.Product.Domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Product.Domain.model.Product;

public interface IProductAddService {
     public ResponseEntity<String> addProducts(List<Product> productDTOList);
}
