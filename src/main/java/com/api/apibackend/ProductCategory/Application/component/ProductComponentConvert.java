package com.api.apibackend.ProductCategory.Application.component;

import org.springframework.stereotype.Component;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;

@Component
public class ProductComponentConvert {
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
