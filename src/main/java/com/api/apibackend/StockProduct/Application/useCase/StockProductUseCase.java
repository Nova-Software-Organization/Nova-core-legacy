package com.api.apibackend.StockProduct.Application.useCase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.StockProduct.Domain.model.StockProduct;

@Service
public class StockProductUseCase {
    private final ProductRepository productRepository;
    private final StockProduct stockProduct;

    @Autowired
    public StockProductUseCase(ProductRepository productRepository, StockProduct stockProduct) {
        this.productRepository = productRepository;
        this.stockProduct = stockProduct;
    }

    public boolean checkProductStock(Long idProduct, int quantityToCheck) {
        Optional<ProductEntity> product = productRepository.findById(idProduct);
        if (product != null) {
            return stockProduct.hasEnoughStock(quantityToCheck);
        }
        return false;
    }
}
