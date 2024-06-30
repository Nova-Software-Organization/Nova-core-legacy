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
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Midia.infra.persistence.repository.MidiaRepository;
import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ProductResponse;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Application.component.ProductComponentAdd;
import com.api.apibackend.modules.Product.Application.converter.ProductDetailsResponseConverter;
import com.api.apibackend.modules.Product.Application.converter.ProductResponseConverter;
import com.api.apibackend.modules.Product.Domain.repository.IProductConventionalService;
import com.api.apibackend.modules.Product.Domain.specs.ProductVariantSpecs;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;
import com.api.apibackend.modules.Product.Infra.useCases.ProductCacheService;
import com.api.apibackend.modules.Product.Infra.useCases.ProductVariantCacheService;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.repository.ProductCategoryRepository;
import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantResponse;
import com.api.apibackend.modules.ProductVariant.Application.converter.ProductVariantResponseConverter;
import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;
import com.api.apibackend.modules.ProductVariant.infra.persistence.repository.ProductVariantRepository;
import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;
import com.api.apibackend.modules.Stock.Infra.persistence.repository.StockRepository;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.Unity.infra.persistence.entity.UnityEntity;
import com.api.apibackend.modules.Unity.infra.persistence.repository.UnityRepository;
import com.api.apibackend.shared.error.exception.InvalidArgumentException;

@Service
public class ProductConventionalService implements IProductConventionalService {
    private ProductCategoryRepository productCategoryRepository;
    private MidiaRepository midiaRepository;
    private ProductRepository productRepository;
    private ProductComponentAdd productComponentAdd;
    private UnityRepository unityRepository;
    private StockRepository stockRepository;
    private ProductVariantCacheService productVariantCacheService;
    private ProductVariantRepository productVariantRepository;
    private ProductResponseConverter productResponseConverter;
    private ProductVariantResponseConverter productVariantResponseConverter;
    private ProductDetailsResponseConverter productDetailsResponseConverter;


    @Autowired
    public ProductConventionalService(
            ProductCategoryRepository productCategoryRepository,
            MidiaRepository midiaRepository, ProductRepository productRepository,
            ProductComponentAdd productComponentAdd,
            UnityRepository unityRepository,
            StockRepository stockRepository,
            ProductVariantRepository productVariantRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.midiaRepository = midiaRepository;
        this.productRepository = productRepository;
        this.productComponentAdd = productComponentAdd;
        this.unityRepository = unityRepository;
        this.stockRepository = stockRepository;
        this.productVariantRepository = productVariantRepository;
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
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("Erro detectado na criação do produto",
             this.getClass().getName(), String.join("\n", 
             errorMessages
             )));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Produtos Adicionados com sucesso!",
         this.getClass().getName(), null));
    }

    public ProductVariantEntity findProductVariantById(Long id) {
        ProductVariantEntity productVariant = productVariantCacheService.findById(id);
        if (Objects.isNull(productVariant)) {
            throw new ResourceNotFoundException(String.format("Não foi possível encontrar nenhuma variante do produto com o ID %d", id));
        }
        return productVariant;
    }

    public List<ProductVariantResponse> getAll(Integer page, Integer size, String sort, String category, Float minPrice, Float maxPrice, String color) {
        PageRequest pageRequest;
        if (Objects.nonNull(sort) && !sort.isBlank()) {
            Sort sortRequest = getSort(sort);
            if (Objects.isNull(sortRequest)) {
                throw new InvalidArgumentException("Parâmetro de classificação inválido");
            }
            pageRequest = PageRequest.of(page.intValue(), size.intValue(), sortRequest);
        } else {
            pageRequest = PageRequest.of(page.intValue(), size.intValue());
        }
    
        Specification<ProductVariantEntity> combinations =
                Objects.requireNonNull(Specification.where(ProductVariantSpecs.withColor(color)))
                        .and(ProductVariantSpecs.withCategory(category))
                        .and(ProductVariantSpecs.minPrice(minPrice))
                        .and(ProductVariantSpecs.maxPrice(maxPrice));
    
        return productVariantRepository.findAll(combinations, pageRequest)
                .stream()
                .map(productVariantResponseConverter)
                .collect(Collectors.toList());
    }

    public Long getAllCount(String category, Float minPrice, Float maxPrice, String color) {
        Specification<ProductVariantEntity> combinations =
                Objects.requireNonNull(Specification.where(ProductVariantSpecs.withColor(color)))
                        .and(ProductVariantSpecs.withCategory(category))
                        .and(ProductVariantSpecs.minPrice(minPrice))
                        .and(ProductVariantSpecs.maxPrice(maxPrice));

        return productVariantRepository.count(combinations);
    }

    public List<ProductVariantResponse> getMostSelling() {
        List<ProductVariantEntity> productVariants = productVariantCacheService.findTop8ByOrderBySellCountDesc();
        if (productVariants.isEmpty()) {
            throw new ResourceNotFoundException("Produtos mais vendidos não encontrados");
        }

        return productVariants
                .stream()
                .map(productVariantResponseConverter)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> searchProductDisplay(String keyword, Integer page, Integer size) {
        if (Objects.isNull(page) || Objects.isNull(size)) {
            throw new InvalidArgumentException("Page and size are required");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        List<ProductEntity> products = productRepository.findAllByNameContainingIgnoreCase(keyword, pageRequest);
        return products
                .stream()
                .map(productResponseConverter)
                .collect(Collectors.toList());
    }

    private Sort getSort(String sort) {
        switch (sort) {
            case "lowest":
                return Sort.by(Sort.Direction.ASC, "price");
            case "highest":
                return Sort.by(Sort.Direction.DESC, "price");
            default:
                return null;
        }
    }
}
