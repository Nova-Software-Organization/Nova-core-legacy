/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.ProductSupplierFilter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Domain.service.ProductFilterService;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

@Service
public class ProductFIlterSupplierUseCase {
    private ProductFilterService productFilterService;

    @Autowired
    public ProductFIlterSupplierUseCase(ProductFilterService productFilterService) {
        this.productFilterService = productFilterService;
    }

    public List<ProductEntity> execute(Long supplierId) {
        List<ProductEntity> productsBySupplier = productFilterService.filterProductsBySupplier(supplierId);
        return productsBySupplier;
    }
}
