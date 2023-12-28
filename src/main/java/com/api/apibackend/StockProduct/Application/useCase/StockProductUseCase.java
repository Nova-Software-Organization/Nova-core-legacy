package com.api.apibackend.StockProduct.Application.useCase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Cart.Domain.exception.CartNotFoundException;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;
import com.api.apibackend.StockProduct.Domain.model.StockProduct;

@Service
public class StockProductUseCase {
    private ProductRepository productRepository;
    private StockProduct stockProduct;

    @Autowired
    public StockProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean checkProductStock(Long idProduct, int quantityToCheck) throws CartNotFoundException {
        try {
            Optional<ProductEntity> productOptional = productRepository.findById(idProduct);

            if (productOptional.isPresent()) {
                ProductEntity product = productOptional.get();
                if (stockProduct.hasEnoughStock(product.getQuantityInStock(), quantityToCheck)) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            throw new CartNotFoundException("Erro ao verificar o estoque do produto");
        }
    }

}
