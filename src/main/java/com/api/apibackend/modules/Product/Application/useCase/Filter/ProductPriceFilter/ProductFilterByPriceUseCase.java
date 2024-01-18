/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.ProductPriceFilter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Domain.service.ProductFilterService;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

@Service
public class ProductFilterByPriceUseCase {
    private ProductFilterService productFilterService;

    @Autowired
    public ProductFilterByPriceUseCase(ProductFilterService productFilterService) {
        this.productFilterService = productFilterService;
    }

    public List<ProductEntity> execute(BigDecimal minPrice, BigDecimal maxPrice) {
        List<ProductEntity> productsInRange = productFilterService.filterProductsByPrice(minPrice, maxPrice);
        return productsInRange;
    }
}
