package com.api.apibackend.modules.ProductVariant.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;
import com.api.apibackend.modules.ProductVariant.infra.persistence.repository.ProductVariantRepository;

@Service
public class ProductVariantGetByIdService {
  private final ProductVariantRepository productVariantRepository;

  @Autowired
  public ProductVariantGetByIdService(ProductVariantRepository productVariantRepository) {
      this.productVariantRepository = productVariantRepository;
  }

  public ProductVariantEntity findProductVariantById(Long id) {
    Optional<ProductVariantEntity> variantOptional = Optional.ofNullable(productVariantRepository.findById(id));
    return variantOptional.orElse(null);
}
}
