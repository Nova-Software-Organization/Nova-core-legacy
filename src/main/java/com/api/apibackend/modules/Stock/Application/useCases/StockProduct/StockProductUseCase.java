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
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;
import com.api.apibackend.modules.Stock.Domain.model.StockProduct;

@Service
public class StockProductUseCase {

    private final ProductRepository productRepository;
    private final StockProduct stockProduct;

    @Autowired
    public StockProductUseCase(ProductRepository productRepository, StockProduct stockProduct) {
        this.productRepository = productRepository;
        this.stockProduct = stockProduct;
    }

    /**
     * Verifica a quantidade de estoque dos produtos.
     * 
     * @param productCheckQuantities Lista de produtos a serem verificados com suas quantidades.
     * @return Lista de IDs dos produtos que estão indisponíveis em estoque.
     * @throws CartNotFoundException Se ocorrer algum erro ao verificar o estoque.
     */
    public List<Long> checkProductStock(List<ProductCheckQuantity> productCheckQuantities)
            throws CartNotFoundException {
        List<Long> unavailableProducts = new ArrayList<>();
        try {
            // Extrai todos os IDs de produtos a serem verificados
            List<Long> allProductIds = productCheckQuantities.stream()
                    .flatMap(productQuantity -> productQuantity.getIdProduct().stream())
                    .collect(Collectors.toList());

            // Obtém os produtos do repositório com base nos IDs
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
            throw new CartNotFoundException("Erro ao verificar o estoque do produto", e);
        }
    }
}
