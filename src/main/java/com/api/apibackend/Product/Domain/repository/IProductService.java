package com.api.apibackend.Product.Domain.repository;

import java.util.List;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.infra.entity.ProductEntity;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;


public interface IProductService {
	
	List<Product> getProductsByCategoryName(String categoryName);

	Product convertToProductDTO(ProductEntity product, ProductCategoryEntity category);
}
