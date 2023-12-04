package com.api.apibackend.Order.Domain.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Order.Application.controller.OrderRequest;
import com.api.apibackend.Order.Domain.repository.IUpdateOrderService;
import com.api.apibackend.Order.infra.entity.OrderEntity;
import com.api.apibackend.Order.infra.repository.OrderRepository;
import com.api.apibackend.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.Product.infra.entity.ProductEntity;
import com.api.apibackend.Product.infra.repository.ProductRepository;

@Service
public class UpdateOrderService implements IUpdateOrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = LogManager.getLogger(UpdateOrderService.class);
    
    @Autowired
    private ProductRepository productRepository;
     
     public ResponseEntity<OrderEntity> updateAddressOrder(OrderRequest numberOrder) {
        Optional<OrderEntity> order = orderRepository.findById(numberOrder.getNumberOrder());
        
        if (order.isPresent()) {
           OrderEntity updateOrder = order.get();
           updateOrder.getClient().getAddress();

           updateOrder.setHousenumber(numberOrder.getCustomerAddress().getNeighborhood());
           updateOrder.setHousenumber(numberOrder.getCustomerAddress().getHousenumber());
           updateOrder.setCep(numberOrder.getCustomerAddress().getCep());
           updateOrder.setRoad(numberOrder.getCustomerAddress().getRoad());
           
           logger.error("Atualização do endereço feita com sucesso!");
           return ResponseEntity.ok(orderRepository.save(updateOrder));
        }

        logger.error("Atualização do endereço não pode ser feita!, endereço não encontrado");
        return ResponseEntity.notFound().build();
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
        
        logger.error("Atualização não pode ser concluida, não encontrado");
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<OrderEntity> canceladOrder(OrderRequest numberOrder) {
      Optional<OrderEntity> order = orderRepository.findById(numberOrder.getNumberOrder());
      
      if (order.isPresent()) {
         OrderEntity updateOrder = order.get();
         updateOrder.setStatus("cancelado");
         logger.error("Status do pedido foi alterado com sucesso");
         return ResponseEntity.ok(orderRepository.save(updateOrder));
      }

      logger.error("Atualização não pode ser concluida, pedido não encontrado");
      return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> deleteOrder(Long id) {
      Optional<OrderEntity> optionalOrder = orderRepository.findById(id);

      if (optionalOrder.isPresent()) {
          orderRepository.delete(optionalOrder.get());
          return ResponseEntity.noContent().build();
      }

      logger.error("Pedido não pode ser deletado, pedido não encontrado");
      return ResponseEntity.notFound().build();
    }
}
