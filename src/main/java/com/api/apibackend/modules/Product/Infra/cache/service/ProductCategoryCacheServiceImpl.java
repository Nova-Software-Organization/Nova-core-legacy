package com.api.apibackend.modules.Product.Infra.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Infra.useCases.ProductCategoryCacheService;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.repository.ProductCategoryRepository;

import java.util.List;


@Service
@CacheConfig(cacheNames = "product_category")
public class ProductCategoryCacheServiceImpl implements ProductCategoryCacheService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryCacheServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<ProductCategoryEntity> findAllByOrderByName() {
        return productCategoryRepository.findAllByOrderByName();
    }
}
