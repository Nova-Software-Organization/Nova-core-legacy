package com.api.apibackend.OrderItem.Domain.model;

import java.util.ArrayList;
import java.util.List;

import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;

import lombok.Data;

@Data
public class OrderItem {
  private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private List<OrderItem> items;
    private ProductEntity product;

    public List<ProductEntity> getProducts() {
        List<ProductEntity> products = new ArrayList<>();

        if (items != null) {
            for (OrderItem item : items) {
                ProductEntity product = new ProductEntity();
                product.setIdProduct(item.getProductId());
                product.setName(item.getProductName());
                PriceEntity price = new PriceEntity();
                price.setPrice(item.getPrice());
                product.setPriceEntity(price);

                products.add(product);
            }
        }

        return products;
    }
}
