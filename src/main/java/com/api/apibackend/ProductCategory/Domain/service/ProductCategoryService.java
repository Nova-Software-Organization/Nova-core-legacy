package com.api.apibackend.ProductCategory.Domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.Application.component.ProductComponentConvert;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.persistence.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {
	private ProductCategoryRepository productCategoryRepository;
	private ProductRepository productRepository;
	private ProductComponentConvert productComponentConvert;
	
	@Autowired
	public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository, ProductComponentConvert productComponentConvert) {
		this.productCategoryRepository = productCategoryRepository;
		this.productRepository = productRepository;
		this.productComponentConvert = productComponentConvert;
	}

	public List<Product> getProductsByCategoryName(String categoryName) {
		ProductCategoryEntity category = productCategoryRepository.findCategoryByName(categoryName);

		if (category != null) {
			List<ProductEntity> products = productRepository.findByCategory(category);
			List<Product> productDTOs = new ArrayList<>();

			for (ProductEntity product : products) {
				productDTOs.add(productComponentConvert.convertToProductDTO(product, category));
			}
			
			return productDTOs;
		}

		return Collections.emptyList();
	}
}
