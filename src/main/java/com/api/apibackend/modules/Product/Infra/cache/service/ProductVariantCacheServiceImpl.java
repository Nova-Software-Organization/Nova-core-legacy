package com.api.apibackend.modules.Product.Infra.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Infra.useCases.ProductVariantCacheService;
import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;
import com.api.apibackend.modules.ProductVariant.infra.persistence.repository.ProductVariantRepository;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product_variant")
public class ProductVariantCacheServiceImpl implements ProductVariantCacheService {
    private final ProductVariantRepository productVariantRepository;

    @Autowired
    public ProductVariantCacheServiceImpl(ProductVariantRepository productVariantRepository) {
        this.productVariantRepository = productVariantRepository;
    }

    @Override
    @Cacheable(key = "{#root.methodName,#id}")
    public ProductVariantEntity findById(Long id) {
        return productVariantRepository.findById(id);
    }

    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<ProductVariantEntity> findTop8ByOrderBySellCountDesc() {
        return productVariantRepository.findTop8ByOrderBySellCountDesc();
    }
}
