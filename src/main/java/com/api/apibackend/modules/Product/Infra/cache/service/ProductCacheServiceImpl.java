package com.api.apibackend.modules.Product.Infra.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Midia.infra.persistence.repository.MidiaRepository;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;
import com.api.apibackend.modules.Product.Infra.useCases.ProductCacheService;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;

import java.util.List;


@Service
@CacheConfig(cacheNames = "product")
public class ProductCacheServiceImpl implements ProductCacheService {
    private final MidiaRepository midiaRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductCacheServiceImpl(MidiaRepository midiaRepository, ProductRepository productRepository) {
        this.midiaRepository = midiaRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(key = "#url")
    public String findByUrl(String url) {
        return midiaRepository.findByUrl(url).getUrl();
    }


    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<ProductEntity> findTop8ByOrderByDateCreatedDesc() {
        return productRepository.findTop8ByOrderByDateCreatedDesc();
    }

    @Override
    @Cacheable(key = "{#productCategory.name,#id}", unless = "#result.size()==0")
    public List<ProductEntity> getRelatedProducts(ProductCategoryEntity productCategory, Long id) {
        List<ProductEntity> productList = productRepository.findTop8ByProductCategoryAndIdIsNot(productCategory, id);
        if (productList.size() < 8) {
            productList.addAll(productRepository.findAllByProductCategoryIsNot(productCategory, PageRequest.of(0, 8 - productList.size())));
        }
        return productList;
    }
}
