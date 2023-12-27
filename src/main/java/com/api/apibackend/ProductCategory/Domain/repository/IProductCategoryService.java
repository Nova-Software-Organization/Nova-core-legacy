package com.api.apibackend.ProductCategory.Domain.repository;

import java.util.List;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;

public interface IProductCategoryService {
    public List<Product> getProductsByCategoryName(String categoryName);
	public Product convertToProductDTO(ProductEntity productEntity, ProductCategoryEntity category);
}
