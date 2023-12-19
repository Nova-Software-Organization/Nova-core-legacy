package com.api.apibackend.StockProduct.Domain.model;

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

    public boolean hasEnoughStock(int quantityToCheck) {
        return stockQuantity >= quantityToCheck;
    }
}
