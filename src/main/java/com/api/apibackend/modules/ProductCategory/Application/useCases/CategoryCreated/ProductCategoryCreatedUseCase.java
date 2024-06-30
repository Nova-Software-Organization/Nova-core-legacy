/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.ProductCategory.Application.useCases.CategoryCreated;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.modules.ProductCategory.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.ProductCategory.Domain.service.ProductCategoryConventionalService;

@Service
public class ProductCategoryCreatedUseCase {
    private ProductCategoryConventionalService productCategoryCreatedService;

    public ResponseEntity<ResponseMessageDTO> execute(ProductCategoryDTO productCategoryDTO) {
        return productCategoryCreatedService.create(productCategoryDTO);
    }
}
