package com.api.apibackend.modules.Product.Application.useCase.ProductDeactivate;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Domain.service.ProductConventionalService;

@Service
public class ProductDeactivateUseCase {
    private ProductConventionalService productConventionalService;

    @Autowired
    public ProductDeactivateUseCase(ProductConventionalService productConventionalService) {
        this.productConventionalService = productConventionalService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("id do produto inválido", this.getClass().getName(), null));
        }
        
        return productConventionalService.deactivate(id);
    }
}
