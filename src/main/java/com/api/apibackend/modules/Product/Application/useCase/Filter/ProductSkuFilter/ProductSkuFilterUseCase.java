/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.ProductSkuFilter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Domain.service.filter.ProductFilterSkuService;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
@Service
public class ProductSkuFilterUseCase {
    private ProductFilterSkuService productFilterSkuService;

    @Autowired
    public ProductSkuFilterUseCase(ProductFilterSkuService productFilterSkuService) {
        this.productFilterSkuService = productFilterSkuService;
    }
    
    public List<ProductEntity> execute(String sku) {
        return (List<ProductEntity>) productFilterSkuService.filterProductsBySku(sku);
    }
}
