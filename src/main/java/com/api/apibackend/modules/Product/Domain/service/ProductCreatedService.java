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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Midia.infra.persistence.repository.MidiaRepository;
import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Application.component.ProductComponentAdd;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.repository.ProductCategoryRepository;
import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;
import com.api.apibackend.modules.Stock.Infra.persistence.repository.StockRepository;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.Unity.infra.persistence.entity.UnityEntity;
import com.api.apibackend.modules.Unity.infra.persistence.repository.UnityRepository;

@Service
public class ProductCreatedService {
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private MidiaRepository midiaRepository;
    private UnityRepository unityRepository;
    private StockRepository stockRepository;
    private ProductComponentAdd productComponentAdd;

    @Autowired
    public ProductCreatedService(ProductRepository productRepository,
        ProductCategoryRepository productCategoryRepository,
        MidiaRepository midiaRepository,
        UnityRepository unityRepository,
        StockRepository stockRepository,
        ProductComponentAdd productComponentAdd
    ) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.midiaRepository = midiaRepository;
        this.unityRepository = unityRepository;
        this.stockRepository = stockRepository;
        this.productComponentAdd = productComponentAdd;
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
}
