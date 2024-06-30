/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;

@Service
public class ProductUpdateService {
    private ProductRepository productRepository;

    @Autowired
    public ProductUpdateService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> update(Long productId, ProductDTO productDTO) {
        Optional<ProductEntity> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent() && hasDataToUpdate(productDTO)) {
            ProductEntity product = productOptional.get();

            if (productDTO.getName() != null) {
                product.setName(productDTO.getName());
            }

            if (productDTO.getMidia() != null) {
                MidiaEntity midia = new MidiaEntity();
                midia.setCategory(productDTO.getMidia().getCategory());
                midia.setDateCreate(new Date());
                midia.setUrl(productDTO.getMidia().getUrl());
                product.setMidia(midia);
            }

            if (productDTO.getCategory() != null) {
                ProductCategoryEntity category = new ProductCategoryEntity();
                category.setName(productDTO.getCategory().getName());
                category.setStatus(productDTO.getCategory().getStatus());
                category.setTypeCategory(productDTO.getCategory().getTypeCategory());
                product.setCategory(category);
            }

            if (productDTO.getDescription() != null) {
                product.setDescription(productDTO.getDescription());
            }

            if (productDTO.getStatus() != 0) {
                product.setStatus(productDTO.getStatus());
            }

            if (productDTO.getSku() != null) {
                product.setSku(productDTO.getSku());
            }

            productRepository.save(product);
            return ResponseEntity.ok(new ResponseMessageDTO("produto atualizado com sucesso!", this.getClass().getName(), null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean hasDataToUpdate(ProductDTO productDTO) {
        return productDTO != null &&
                (productDTO.getName() != null ||
                        productDTO.getMidia() != null ||
                        productDTO.getCategory() != null ||
                        productDTO.getDescription() != null ||
                        productDTO.getStatus() != 0 ||
                        productDTO.getSku() != null);
    }
}
