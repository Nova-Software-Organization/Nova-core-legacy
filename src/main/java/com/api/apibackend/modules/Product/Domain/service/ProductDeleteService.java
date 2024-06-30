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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

@Service
public class ProductDeleteService {
    private ProductRepository productRepository;

    @Autowired
    public ProductDeleteService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> delete(Long productId) {
        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ProductEntity product = productOptional.get();
        productRepository.delete(product);

        return ResponseEntity.ok(new ResponseMessageDTO("produto deletado com sucesso!", this.getClass().getName(), null));
    }
}
