/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

@Service
public class ProductFilterSkuService {
    private ProductRepository productRepository;

    @Autowired
    public ProductFilterSkuService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<ResponseMessageDTO> filterProductsBySku(String sku) {
        if (sku == null || sku.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessageDTO("O valor de 'sku' é inválido", this.getClass().getName(), null, null));
        }

        List<ProductEntity> filteredProducts = productRepository.findBySku(sku);
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseMessageDTO("Produtos filtrados com sucesso!", this.getClass().getName(), filteredProducts, null));
    }
}
