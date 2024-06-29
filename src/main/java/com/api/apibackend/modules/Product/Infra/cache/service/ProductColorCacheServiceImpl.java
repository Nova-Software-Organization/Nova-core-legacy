package com.api.apibackend.modules.Product.Infra.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Color.infra.persistence.entity.ColorEntity;
import com.api.apibackend.modules.Color.infra.persistence.repository.ColorRepository;
import com.api.apibackend.modules.Product.Infra.useCases.ProductColorCacheService;

import java.util.List;

@Service

@CacheConfig(cacheNames = "product_color")
public class ProductColorCacheServiceImpl implements ProductColorCacheService {

    private final ColorRepository colorRepository;

    @Autowired
    public ProductColorCacheServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<ColorEntity> findAll() {
        return colorRepository.findAll();
    }
}
