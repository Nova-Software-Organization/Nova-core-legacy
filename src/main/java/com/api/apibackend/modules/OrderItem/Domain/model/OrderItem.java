/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.OrderItem.Domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.api.apibackend.modules.Price.infra.entity.PriceEntity;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;

import lombok.Data;

@Data
public class OrderItem {
  private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
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
