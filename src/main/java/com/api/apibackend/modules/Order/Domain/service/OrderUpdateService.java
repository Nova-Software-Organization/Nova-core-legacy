package com.api.apibackend.modules.Order.Domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;
import com.api.apibackend.modules.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderUpdateService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderUpdateService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    public ResponseEntity<OrderEntity> updateOrder(Long id, OrderRequest orderRequest) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            OrderEntity orderEntity = optionalOrder.get();
            orderEntity.clearProducts();
            List<OrderItem> items = orderRequest.getItems();
            items.forEach(item -> {
                Optional<ProductEntity> optionalProduct = productRepository.findById(item.getProductId());
                if (optionalProduct.isPresent()) {
                    ProductEntity productEntity = optionalProduct.get();
                    orderEntity.addProduct(productEntity);
                }
            });

            orderEntity.calculateTotal();

            return ResponseEntity.ok(orderRepository.save(orderEntity));
        }

        log.error("Atualização não pode ser concluida, não encontrado");
        return ResponseEntity.notFound().build();
    }
}
