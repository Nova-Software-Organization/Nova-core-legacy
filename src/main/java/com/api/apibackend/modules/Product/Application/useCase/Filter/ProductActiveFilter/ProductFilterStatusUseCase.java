/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.ProductActiveFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Domain.service.ProductFilterStatusService;

@Service
public class ProductFilterStatusUseCase {
    private ProductFilterStatusService productFilterStatusService;

    @Autowired
    public ProductFilterStatusUseCase(ProductFilterStatusService productFilterStatusService) {
        this.productFilterStatusService = productFilterStatusService;
    }
    

    public ResponseEntity<ResponseMessageDTO> execute(int status) {
        return productFilterStatusService.processProductFiltering(status);
    }
}
