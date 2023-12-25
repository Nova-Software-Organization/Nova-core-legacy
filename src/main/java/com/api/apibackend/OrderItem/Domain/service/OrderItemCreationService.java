package com.api.apibackend.OrderItem.Domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.apibackend.Order.infra.entity.OrderEntity;
import com.api.apibackend.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.OrderItem.infra.entity.OrderItemEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;

@Service
public class OrderItemCreationService {

    private ProductRepository productRepository;

    public OrderItemCreationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<OrderItemEntity> createOrderItems(List<OrderItem> items, OrderEntity orderEntity) {
        if (items == null) {
            throw new IllegalArgumentException("items inexistente!");
        }

        List<OrderItemEntity> orderItems = new ArrayList<>();
        final float[] total = { 0.0f };

        items.forEach(item -> {
            Long productId = item.getProductId();

            if (productId != null) {
                Optional<ProductEntity> optionalProduct = productRepository.findById(productId);

                if (optionalProduct.isPresent()) {
                    ProductEntity productEntity = optionalProduct.get();
                    
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setProduct(productEntity);
                    orderItemEntity.setQuantity(item.getQuantity());
                    orderItemEntity.setUnitPrice(productEntity.getPriceEntity().getPrice());
                    orderItemEntity.setOrder(orderEntity);
                    
                    BigDecimal valuePrice = productEntity.getPriceEntity().getPrice();
                    Double itemTotal = item.getQuantity() * valuePrice.doubleValue();
                    total[0] += itemTotal;

                    orderItems.add(orderItemEntity);
                } else {
                    throw new IllegalArgumentException("Produto não encontrado para o ID: " + productId);
                }
            } else {
                throw new IllegalArgumentException("O campo productId deve ser especificado para cada item do pedido.");
            }
        });

        return orderItems;
    }
}
