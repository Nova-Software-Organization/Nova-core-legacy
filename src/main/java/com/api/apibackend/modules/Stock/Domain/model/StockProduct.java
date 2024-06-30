/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Stock.Domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class StockProduct {
    private Long id;
    private String name;
    private int stockQuantity;

    @Autowired
    public StockProduct() {
    }

    public boolean hasEnoughStock(int quantityStockProduct, int quantityToCheck) {
        return quantityStockProduct >= quantityToCheck;
    }
}
