/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Price.infra.repository.PriceRepository;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

@Service
public class ProductFilterService {
    private ProductRepository productRepository;
    private PriceRepository priceRepository;
    
    public ResponseEntity<ResponseMessageDTO> processProductFiltering(int status) {
        try {
            if (status != 0 && status != 1) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("O valor de 'status' deve ser 0 (inativo) ou 1 (ativo)", this.getClass().getName(), null, null));
            }

            List<ProductEntity> filteredProducts = productRepository.findByStatus(status);
            ResponseMessageDTO responseMessage = new ResponseMessageDTO("Produtos filtrados com sucesso", this.getClass().getName(), filteredProducts, null );

            return ResponseEntity.ok().body(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }

    public List<ProductEntity> filterProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return priceRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<ProductEntity> filterProductsBySku(String sku) {
        return productRepository.findBySku(sku);
    }

     public List<ProductEntity> filterProductsBySupplier(Long supplierId) {
        return productRepository.findBySupplierEntity(supplierId);
    }
}
