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
import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Product.Application.component.ProductComponentAdd;
import com.api.apibackend.Product.Domain.repository.IProductConventionalService;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.ProductCategory.infra.persistence.repository.ProductCategoryRepository;
import com.api.apibackend.Stock.infra.persistence.entity.StockEntity;
import com.api.apibackend.Stock.infra.persistence.repository.StockRepository;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Unity.infra.persistence.entity.UnityEntity;
import com.api.apibackend.Unity.infra.persistence.repository.UnityRepository;

@Service
public class ProductConventionalService implements IProductConventionalService {
    private ProductCategoryRepository productCategoryRepository;
    private MidiaRepository midiaRepository;
    private ProductRepository productRepository;
    private ProductComponentAdd productComponentAdd;
    private UnityRepository unityRepository;
    private StockRepository stockRepository;

    @Autowired
    public ProductConventionalService(
            ProductCategoryRepository productCategoryRepository,
            MidiaRepository midiaRepository, ProductRepository productRepository,
            ProductComponentAdd productComponentAdd,
            UnityRepository unityRepository,
            StockRepository stockRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.midiaRepository = midiaRepository;
        this.productRepository = productRepository;
        this.productComponentAdd = productComponentAdd;
        this.unityRepository = unityRepository;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public ResponseEntity<ResponseMessageDTO> populationProduct(List<ProductDTO> productDTOList) {
        List<String> errorMessages = new ArrayList<>();

        for (ProductDTO productDTO : productDTOList) {
            try {
                MidiaEntity midia = new MidiaEntity();
                midia.setUrl(productDTO.getMidia().getUrl());
                midia.setCategory(productDTO.getCategory().getName());
                midia.setDateCreate(new Date());
                midia = midiaRepository.save(midia);

                ProductCategoryEntity category = new ProductCategoryEntity();
                category.setName(productDTO.getCategory().getName());
                category.setStatus(productDTO.getCategory().getStatus());
                category.setTypeCategory(productDTO.getCategory().getTypeCategory());
                category = productCategoryRepository.save(category);

                UnityEntity unity = new UnityEntity();
                unity.setName(productDTO.getUnityEntity().getName());
                unity.setAbbreviation(productDTO.getUnityEntity().getAbbreviation());
                unity = unityRepository.save(unity);

                SupplierEntity supplier = productComponentAdd.supplierSave(productDTO);

                StockEntity stock = new StockEntity();
                stock.setInput_quantity(productDTO.getStockEntity().getQuantity());
                stock.setDateMoviment(new Date());
                stock.setTypeMoviment("ENTRADA");
                stock.setUnityEntity(unity);

                ProductEntity newProduct = new ProductEntity();
                newProduct.setName(productDTO.getName());
                newProduct.setDescription(productDTO.getDescription());
                newProduct.setStatus(productDTO.getStatus());
                newProduct.setSku(productDTO.getSku());
                newProduct.setCategory(category);
                newProduct.setMidia(midia);
                newProduct.setStockEntity(stock);
                newProduct.setSupplierEntity(supplier);
                newProduct.setUnityEntity(unity);
                newProduct = productRepository.save(newProduct);
                stock.setProductEntity(newProduct);
                stock = stockRepository.save(stock);

                productComponentAdd.priceSaveProduct(productDTO, newProduct);

            } catch (RuntimeException e) {
                errorMessages.add("Erro ao tentar adicionar os produtos: " + e.getMessage());
            }
        }

        if (!errorMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("erro detectado na criação do produto", this.getClass().getName(), String.join("\n", errorMessages)));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Produtos Adicionados com sucesso!", this.getClass().getName(), null));
    }

    public ResponseEntity<ResponseMessageDTO> create(ProductDTO productDTO) {
        Optional<ProductEntity> productOptional = productRepository.findByName(productDTO.getName());
        if (productOptional.isPresent() && productOptional.equals(productDTO)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseMessageDTO("O produto não pode ser adicionado por que tem outro igual!", this.getClass().getName(), null));
        }

        MidiaEntity midia = new MidiaEntity();
        midia.setUrl(productDTO.getMidia().getUrl());
        midia.setCategory(productDTO.getCategory().getName());
        midia.setDateCreate(new Date());
        midia = midiaRepository.save(midia);

        ProductCategoryEntity category = new ProductCategoryEntity();
        category.setName(productDTO.getCategory().getName());
        category.setStatus(productDTO.getCategory().getStatus());
        category.setTypeCategory(productDTO.getCategory().getTypeCategory());
        category = productCategoryRepository.save(category);

        UnityEntity unity = new UnityEntity();
        unity.setName(productDTO.getUnityEntity().getName());
        unity.setAbbreviation(productDTO.getUnityEntity().getAbbreviation());
        unity = unityRepository.save(unity);

        SupplierEntity supplier = productComponentAdd.supplierSave(productDTO);

        StockEntity stock = new StockEntity();
        stock.setInput_quantity(productDTO.getStockEntity().getQuantity());
        stock.setDateMoviment(new Date());
        stock.setTypeMoviment("ENTRADA");
        stock.setUnityEntity(unity);

        ProductEntity newProduct = new ProductEntity();
        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setStatus(productDTO.getStatus());
        newProduct.setSku(productDTO.getSku());
        newProduct.setCategory(category);
        newProduct.setMidia(midia);
        newProduct.setStockEntity(stock);
        newProduct.setSupplierEntity(supplier);
        newProduct.setUnityEntity(unity);
        newProduct = productRepository.save(newProduct);
        stock.setProductEntity(newProduct);
        stock = stockRepository.save(stock);

        productComponentAdd.priceSaveProduct(productDTO, newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Produto criado com sucesso", this.getClass().getName(), null));
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
