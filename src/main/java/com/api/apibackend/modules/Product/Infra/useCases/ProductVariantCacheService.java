package com.api.apibackend.modules.Product.Infra.useCases;

import java.util.List;

import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;

public interface ProductVariantCacheService {
    ProductVariantEntity findById(Long id);
    List<ProductVariantEntity> findTop8ByOrderBySellCountDesc();
}
