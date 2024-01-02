/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.OrderItem.Domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Modules.OrderItem.Domain.exception.NonExistentesItemsException;
import com.api.apibackend.Modules.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.Modules.OrderItem.infra.persistence.entity.OrderItemEntity;
import com.api.apibackend.Modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Modules.Product.Infra.repository.ProductRepository;

@Service
public class OrderItemCreationService {
    private ProductRepository productRepository;

    @Autowired
    public OrderItemCreationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<OrderItemEntity> createOrderItems(List<OrderItem> items, OrderEntity orderEntity) throws NonExistentesItemsException {
        if (items == null) {
            throw new NonExistentesItemsException("items inexistente!");
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
