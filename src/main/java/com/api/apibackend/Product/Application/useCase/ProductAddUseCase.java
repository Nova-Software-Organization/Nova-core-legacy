package com.api.apibackend.Product.Application.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Product.Domain.service.ProductConventionalService;

@Service
public class ProductAddUseCase {
    private ProductConventionalService productConventionalService;
    
    @Autowired
    public ProductAddUseCase(ProductConventionalService productConventionalService) {
        this.productConventionalService = productConventionalService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(List<ProductDTO> productDTOList) {
        if (productDTOList == null || productDTOList.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("A lista de produtos está vazia ou nula.", this.getClass().getName(), null));
        }

        for (ProductDTO product : productDTOList) {
            if (product.getName() == null || product.getName().isEmpty()) {
                return ResponseEntity.badRequest().body(new ResponseMessageDTO("O nome do produto é obrigatório.", this.getClass().getName(), null));
            }

            if (product.getPriceEntity().getPrice().intValue() <= 0) {
                return ResponseEntity.badRequest().body(new ResponseMessageDTO("O preço do produto deve ser um número positivo.", this.getClass().getName(), null));
            }
        }

        return productConventionalService.populationProduct(productDTOList);
    }
}
