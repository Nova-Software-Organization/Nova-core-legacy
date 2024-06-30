package com.api.apibackend.modules.Product.Infra.useCases;

import java.util.List;

import com.api.apibackend.modules.Color.infra.persistence.entity.ColorEntity;

public interface ProductColorCacheService {
    List<ColorEntity> findAll();
}
