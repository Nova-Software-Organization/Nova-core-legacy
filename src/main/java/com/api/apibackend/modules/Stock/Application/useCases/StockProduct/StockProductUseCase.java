/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Stock.Application.useCases.StockProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Cart.Application.DTOs.ProductCheckQuantity;
import com.api.apibackend.modules.Cart.Domain.exception.CartNotFoundException;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.repository.ProductRepository;
import com.api.apibackend.modules.Stock.Domain.model.StockProduct;

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
            List<Long> allProductIds = productCheckQuantities.stream()
                    .flatMap(productQuantity -> productQuantity.getIdProduct().stream())
                    .collect(Collectors.toList());

            List<ProductEntity> products = productRepository.findByIds(allProductIds);
            for (ProductCheckQuantity productCheckQuantity : productCheckQuantities) {
                List<Long> idProducts = productCheckQuantity.getIdProduct();
                int quantityToCheck = productCheckQuantity.getQuantityToCheck();

                // Filtra a lista de produtos para o ID atual
                List<ProductEntity> currentProducts = products.stream()
                        .filter(p -> idProducts.contains(p.getIdProduct()))
                        .collect(Collectors.toList());

                // Verifica o estoque para cada produto
                for (ProductEntity product : currentProducts) {
                    if (!stockProduct.hasEnoughStock(product.getStockEntity().getInput_quantity(), quantityToCheck)) {
                        unavailableProducts.add(product.getIdProduct());
                    }
                }
            }

            return unavailableProducts;
        } catch (Exception e) {
            throw new CartNotFoundException("Erro ao verificar o estoque do produto");
        }
    }
}
