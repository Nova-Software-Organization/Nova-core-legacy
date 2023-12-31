package com.api.apibackend.StockProduct.Domain.model;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class StockProduct {
    private Long id;
    private String name;
    private int stockQuantity;

    public StockProduct(Long id, String name, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public boolean hasEnoughStock(int quantityStockProduct, int quantityToCheck) {
        return quantityStockProduct >= quantityToCheck;
    }
}
