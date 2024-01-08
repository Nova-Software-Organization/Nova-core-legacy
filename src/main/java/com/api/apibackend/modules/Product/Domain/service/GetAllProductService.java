/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Product.Domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Price.infra.entity.PriceEntity;
import com.api.apibackend.modules.Product.Domain.model.Product;
import com.api.apibackend.modules.Product.Domain.repository.IGetAllProductService;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.repository.ProductRepository;
import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;

@Service
public class GetAllProductService implements IGetAllProductService {
    private ProductRepository productRepository;
    
    @Autowired
    public GetAllProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "produtos", key = "'getAllProductsFromCache'")
    public List<Product> getAllProductsFromCache() {
        return productRepository.findAll()
            .stream()
            .map(this::mapToProduct)
            .collect(Collectors.toList());
    }

    @Override
    public Product mapToProduct(ProductEntity product) {
        MidiaEntity midia = product.getMidia();
        PriceEntity price = product.getPriceEntity();
        StockEntity stock = product.getStockEntity();
        return new Product(
            product.getIdProduct(),
            product.getName(),
            midia != null ? midia.getUrl() : null,
            product.getDescription(),
            product.getCategory().getName(),
            price.getPrice(),
            price.getDiscountPrice(),
            stock.getInput_quantity()
            );
        }

    public List<Product> listProducts() {
        return getAllProductsFromCache();
    }
}
