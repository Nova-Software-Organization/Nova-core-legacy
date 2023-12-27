package com.api.apibackend.ProductCategory.Domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.repository.ProductCategoryRepository;

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

		if (category != null) {
			List<ProductEntity> products = productRepository.findByCategory(category);

			List<Product> productDTOs = new ArrayList<>();
			for (ProductEntity product : products) {
				productDTOs.add(convertToProductDTO(product, category));
			}
			return productDTOs;
		}

		return Collections.emptyList();
	}

	public Product convertToProductDTO(ProductEntity productEntity, ProductCategoryEntity category) {
		if (productEntity == null) {
			return null;
		}

		Product product = new Product();
		product.setId(product.getId());
		product.setName(product.getName());
		product.setUrl(productEntity.getMidia().getUrl());
		product.setDescription(product.getDescription());
		product.setCategory(category != null ? category.getName() : null);
		product.setPrice(product.getPrice());
		product.setQuantityInStock(product.getQuantityInStock());

		return product;
	}
}
