package com.api.apibackend.Product.Application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.infra.entity.ProductEntity;
import com.api.apibackend.Product.infra.repository.ProductRepository;

@Service
public class GetAllProductService {
    
    @Autowired
	private ProductRepository productRepository;

    @Cacheable(value = "produtos", key = "'getAllProductsFromCache'")
    public List<Product> getAllProductsFromCache() {
        return productRepository.findAll()
            .stream()
            .map(this::mapToProduct)
            .collect(Collectors.toList());
    }

    private Product mapToProduct(ProductEntity product) {
        MidiaEntity midia = product.getMidia();
        return new Product(
            product.getId(),
            product.getName(),
            midia != null ? midia.getUrl() : null,
            product.getDescription(),
            product.getCategory().getName(),
            product.getPrice(),
            product.getDePrice(),
            product.getQuantityInStock()
            );
        }

    public List<Product> execute() {
        return getAllProductsFromCache();
    }
}
