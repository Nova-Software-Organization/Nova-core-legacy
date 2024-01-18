/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Midia.infra.persistence.repository.MidiaRepository;
import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Application.component.ProductComponentAdd;
import com.api.apibackend.modules.Product.Domain.repository.IProductConventionalService;
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
}
