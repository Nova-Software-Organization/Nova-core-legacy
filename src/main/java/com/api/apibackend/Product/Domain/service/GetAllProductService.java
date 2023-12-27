package com.api.apibackend.Product.Domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.repository.IGetAllProductService;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;

@Service
public class GetAllProductService implements IGetAllProductService {
    private ProductRepository productRepository;
    
    @Autowired
    public GetAllProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "produtos", key = "'getAllProductsFromCache'")
    public List<Product> getAllProductsFromCache() {
        return productRepository.findAll()
            .stream()
            .map(this::mapToProduct)
            .collect(Collectors.toList());
    }

    @Override
    public Product mapToProduct(ProductEntity product) {
        MidiaEntity midia = product.getMidia();
        PriceEntity price = product.getPriceEntity();
        return new Product(
            product.getIdProduct(),
            product.getName(),
            midia != null ? midia.getUrl() : null,
            product.getDescription(),
            product.getCategory().getName(),
            price.getPrice(),
            price.getDiscountPrice(),
            product.getQuantityInStock()
            );
        }

    public List<Product> execute() {
        return getAllProductsFromCache();
    }
}
