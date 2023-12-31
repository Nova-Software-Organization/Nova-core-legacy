package com.api.apibackend.StockProduct.Application.useCase;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Cart.Application.DTOs.ProductCheckQuantity;
import com.api.apibackend.Cart.Domain.exception.CartNotFoundException;
import com.api.apibackend.Product.Domain.exception.ProductNotFoundException;
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

    public List<Long> checkProductStock(List<ProductCheckQuantity> productCheckQuantities)
            throws CartNotFoundException {
        List<Long> unavailableProducts = new ArrayList<>();

        try {
            for (ProductCheckQuantity productCheckQuantity : productCheckQuantities) {
                List<Long> idProduct = productCheckQuantity.getIdProduct();
                int quantityToCheck = productCheckQuantity.getQuantityToCheck();

                Optional<ProductEntity> productOptional = productRepository.findById(idProduct);

                if (productOptional.isPresent()) {
                    ProductEntity product = productOptional.get();
                    if (!stockProduct.hasEnoughStock(product.getStockEntity().getInput_quantity(), quantityToCheck)) {
                        unavailableProducts.addAll(idProduct);
                    }
                } else {
                    // Product not found in the repository
                    throw new ProductNotFoundException("Produto não encontrado para o ID: " + idProduct);
                }
            }

            return unavailableProducts;
        } catch (ProductNotFoundException e) {
            throw new CartNotFoundException("Erro ao verificar o estoque do produto: " + e.getMessage());
        } catch (Exception e) {
            throw new CartNotFoundException("Erro ao verificar o estoque do produto");
        }
    }
}
