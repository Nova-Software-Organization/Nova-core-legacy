/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.ProductUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Domain.service.ProductUpdateService;

@Service
public class ProductUpdateUseCase {
    private ProductUpdateService productUpdateService;

    @Autowired
    public ProductUpdateUseCase(ProductUpdateService productUpdateService) {
        this.productUpdateService = productUpdateService;
    }
    

    public ResponseEntity<ResponseMessageDTO> execute(Long id, ProductDTO productDTO) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID de produto inválido", this.getClass().getName(), null));
        }

        if (productDTO == null) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("produto invalido", this.getClass().getName(), null));
        }

        return productUpdateService.update(id, productDTO);
    }
}
