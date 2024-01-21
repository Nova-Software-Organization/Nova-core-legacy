/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.ProductDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Domain.service.ProductDeleteService;

@Service
public class ProductDeleteUseCase {
    private ProductDeleteService productDeleteService;

    @Autowired
    public ProductDeleteUseCase(ProductDeleteService productDeleteService) {
        this.productDeleteService = productDeleteService;
    }
    
    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("id do produto invalido", this.getClass().getName(), null));
        }
        
        return productDeleteService.delete(id);
    }
}
