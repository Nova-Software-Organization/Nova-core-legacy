package com.api.apibackend.modules.ProductVariant.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;

import java.util.List;

@Repository
public interface ProductVariantRepository extends PagingAndSortingRepository<ProductVariantEntity, Long>,
        JpaSpecificationExecutor<ProductVariantEntity> {
    List<ProductVariantEntity> findTop8ByOrderBySellCountDesc();
    ProductVariantEntity findById(Long id);
}
