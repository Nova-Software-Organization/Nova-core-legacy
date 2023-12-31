package com.api.apibackend.ProductCategory.Domain.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.persistence.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	private ProductCategoryRepository productCategoryRepository;
	private ProductRepository productRepository;
	
	@Autowired
	public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
		this.productCategoryRepository = productCategoryRepository;
		this.productRepository = productRepository;
	}

	public List<Product> getProductsByCategoryName(String categoryName) {
        ProductCategoryEntity category = productCategoryRepository.findCategoryByName(categoryName);

        return category != null ?
                productRepository.findByCategory(category)
                        .stream()
                        .map(productEntity -> {
                            Product product = new Product();
                            product.setId(productEntity.getIdProduct());
                            product.setName(productEntity.getName());
                            product.setUrl(productEntity.getMidia().getUrl());
                            product.setDescription(productEntity.getDescription());
                            product.setCategory(category.getName());
                            return product;
                        })
                        .collect(Collectors.toList()) :
                Collections.emptyList();
    }
}
