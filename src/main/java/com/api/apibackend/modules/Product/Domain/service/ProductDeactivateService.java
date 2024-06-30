/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

@Service
public class ProductDeactivateService {
    private ProductRepository productRepository;

    @Autowired
    public ProductDeactivateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> deactivate(Long productId) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("produto não encontrado!", this.getClass().getName(), null));
        }

        ProductEntity productStatus = product.get();
        productStatus.setStatus(0);
        productRepository.save(productStatus);
        return ResponseEntity.ok(new ResponseMessageDTO("produto desativado com sucesso!", this.getClass().getName(), null));
    }
}
