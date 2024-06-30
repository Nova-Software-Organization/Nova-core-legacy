package com.api.apibackend.modules.Product.Infra.useCases;

import java.util.List;

import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;


public interface ProductCategoryCacheService {
    List<ProductCategoryEntity> findAllByOrderByName();
}
