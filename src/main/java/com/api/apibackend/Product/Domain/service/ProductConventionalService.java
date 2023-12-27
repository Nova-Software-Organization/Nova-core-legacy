package com.api.apibackend.Product.Domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.Midia.infra.repository.MidiaRepository;
import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Application.component.ProductComponentAdd;
import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Domain.repository.IProductConventionalService;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.persistence.repository.ProductCategoryRepository;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

@Service
public class ProductConventionalService implements IProductConventionalService {
    private ProductCategoryRepository productCategoryRepository;
    private MidiaRepository midiaRepository;
    private ProductRepository productRepository;
    private ProductComponentAdd productComponentAdd;

    @Autowired
    public ProductConventionalService(ProductCategoryRepository productCategoryRepository, MidiaRepository midiaRepository, ProductRepository productRepository, ProductComponentAdd productComponentAdd) {
        this.productCategoryRepository = productCategoryRepository;
        this.midiaRepository = midiaRepository;
        this.productRepository = productRepository;
        this.productComponentAdd = productComponentAdd;
    }

    @Transactional
    public ResponseEntity<String> populationProduct(List<Product> productDTOList) {
        List<String> errorMessages = new ArrayList<>();

        for (Product productDTO : productDTOList) {
            try {
                MidiaEntity midia = new MidiaEntity();
                midia.setUrl(productDTO.getUrl());
                midia.setCategory(productDTO.getCategory());
                midia.setDateCreate(new Date());
                midia = midiaRepository.save(midia);

                ProductCategoryEntity category = new ProductCategoryEntity();
                category.setName(productDTO.getCategory());
                category = productCategoryRepository.save(category);

                SupplierEntity supplier = productComponentAdd.supplierSave(productDTO);

                ProductEntity newProduct = new ProductEntity();
                newProduct.setName(productDTO.getName());
                newProduct.setDescription(productDTO.getDescription());
                newProduct.setQuantityInStock(productDTO.getQuantityInStock());
                newProduct.setStatus(productDTO.getStatus());
                newProduct.setCategory(category);
                newProduct.setMidia(midia);
                newProduct.setSupplierEntity(supplier);
                newProduct = productRepository.save(newProduct);
                
                productComponentAdd.priceSaveProduct(productDTO, newProduct);

            } catch (RuntimeException e) {
                errorMessages.add("Erro ao tentar adicionar os produtos: " + e.getMessage());
            }
        }

        if (!errorMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(String.join("\n", errorMessages));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Produtos adicionandos com sucesso!");
    }

    public ResponseEntity<String> create(ProductDTO productDTO) {
        Optional<ProductEntity> productOptional = productRepository.findByName(productDTO.getName());
        if (productOptional.isPresent() && productOptional.equals(productDTO)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("O produto não pode ser adicionado por que tem outro igual!");
        }

        ProductEntity product = new ProductEntity();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setMidia(productDTO.getMidia());
        product.setSku(productDTO.getSku());
        product.setStatus(productDTO.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
    }

    @Transactional
    public ResponseEntity<String> delete(Long productId) {
        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ProductEntity product = productOptional.get();
        productRepository.delete(product);

        return ResponseEntity.ok("Produto deletado com sucesso!");
    }

    @Transactional
    public ResponseEntity<String> update(Long productId, ProductDTO productDTO) {
        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ProductEntity product = productOptional.get();
        product.setName(productDTO.getName());
        product.setMidia(productDTO.getMidia());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setPriceEntity(productDTO.getPriceEntity());
        product.setSupplierEntity(productDTO.getSupplierEntity());
        product.setStatus(productDTO.getStatus());
        product.setSku(productDTO.getSku());

        productRepository.save(product);

        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }

    @Transactional
    public ResponseEntity<String> deactivate(Long productId) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ProductEntity productStatus = product.get();
        productStatus.setStatus(0);

        productRepository.save(productStatus);

        return ResponseEntity.ok("Produto desativado com sucesso!");
    }
}
