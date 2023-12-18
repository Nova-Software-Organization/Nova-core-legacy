package com.api.apibackend.OrderItem.Domain.model;

import java.util.ArrayList;
import java.util.List;

import com.api.apibackend.Product.Infra.entity.ProductEntity;

import lombok.Data;

@Data
public class OrderItem {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private OrderItem[] items;
    private ProductEntity product;

    public List<ProductEntity> getProducts() {
        List<ProductEntity> products = new ArrayList<>();

        for (OrderItem item : items) {
            ProductEntity product = new ProductEntity();
            product.setIdProduct(item.getProductId());
            product.setName(item.getProductName());
            product.setPrice(item.getPrice());

            products.add(product);
        }

        return products;
    }
}
