package com.api.apibackend.modules.Product.Infra.useCases;

import java.util.List;

import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;

public interface ProductCacheService {
    String findByUrl(String url);
    List<ProductEntity> findTop8ByOrderByDateCreatedDesc();
    List<ProductEntity> getRelatedProducts(ProductCategoryEntity productCategory, Long id);
}
