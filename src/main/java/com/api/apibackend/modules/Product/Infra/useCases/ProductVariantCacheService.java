package com.api.apibackend.modules.Product.Infra.useCases;

import java.util.List;

import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;

public interface ProductVariantCacheService {
    ProductEntity findById(Long id);
    List<ProductVariantEntity> findTop8ByOrderBySellCountDesc();
}
