package com.api.apibackend.StockProduct.Domain.model;

import lombok.Data;

@Data
public class StockProduct {
    private int id;
    private String name;
    private int stockQuantity;

    public boolean hasEnoughStock(int quantityToCheck) {
        return stockQuantity >= quantityToCheck;
    }
}
