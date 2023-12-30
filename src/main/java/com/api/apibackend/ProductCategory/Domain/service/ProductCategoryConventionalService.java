package com.api.apibackend.ProductCategory.Domain.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.ProductCategory.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.persistence.repository.ProductCategoryRepository;

@Service
public class ProductCategoryConventionalService {
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryConventionalService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> create(ProductCategoryDTO productCategoryDTO) {
        if (productCategoryDTO.getName() == null || productCategoryDTO.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("O nome da categoria é obrigatório.", this.getClass().getName(), null));
        }

        ProductCategoryEntity category = new ProductCategoryEntity();
        category.setName(productCategoryDTO.getName());
        category.setStatus(productCategoryDTO.getStatus());
        category.setTypeCategory(productCategoryDTO.getTypeCategory());

        productCategoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Categoria criada com sucesso!", this.getClass().getName(), null));
    }

    @Transactional
    public ResponseEntity<String> delete(Long categoryId) {
        Optional<ProductCategoryEntity> categoryOptional = productCategoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProductCategoryEntity category = categoryOptional.get();
        productCategoryRepository.delete(category);

        return ResponseEntity.ok("Categoria deletada com sucesso!");
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> update(Long categoryId, ProductCategoryDTO productCategoryDTO) {
        Optional<ProductCategoryEntity> categoryOptional = productCategoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProductCategoryEntity category = categoryOptional.get();
        category.setName(productCategoryDTO.getName());
        category.setStatus(productCategoryDTO.getStatus());
        category.setTypeCategory(productCategoryDTO.getTypeCategory());

        productCategoryRepository.save(category);

        return ResponseEntity.ok(new ResponseMessageDTO("Categoria atualizada com sucesso", this.getClass().getName(), null));
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> deactivate(Long categoryId) {
        Optional<ProductCategoryEntity> categoryOptional = productCategoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProductCategoryEntity category = categoryOptional.get();
        category.setStatus(0);

        productCategoryRepository.save(category);

        return ResponseEntity.ok(new ResponseMessageDTO("Categoria desativada com sucesso", this.getClass().getName(), null));
    }
}
