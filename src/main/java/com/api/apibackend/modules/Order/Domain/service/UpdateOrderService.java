package com.api.apibackend.modules.Order.Domain.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.modules.Order.Domain.repository.IUpdateOrderService;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.repository.OrderAddressRepository;
import com.api.apibackend.modules.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

@Service
public class UpdateOrderService implements IUpdateOrderService {
    private static final Logger logger = LogManager.getLogger(UpdateOrderService.class);
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderAddressRepository orderAddressRepository;

    @Autowired
    public UpdateOrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderAddressRepository orderAddressRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderAddressRepository = orderAddressRepository;
    }

    public ResponseEntity<OrderAddressEntity> updateAddressOrder(OrderUpdateAddressRequest numberOrder) {
        Optional<OrderEntity> order = orderRepository.findById(numberOrder.getNumberOrder());

        if (order.isPresent()) {
            OrderAddressEntity updateOrder = order.get().getOrderAddressEntity();

            updateOrder.setNeighborhood(numberOrder.getCustomerAddressRequest().getNeighborhood() != updateOrder.getNeighborhood() ? numberOrder.getCustomerAddressRequest().getNeighborhood() : updateOrder.getNeighborhood());
            updateOrder.setHousenumber(numberOrder.getCustomerAddressRequest().getHousenumber() != updateOrder.getHousenumber() ? numberOrder.getCustomerAddressRequest().getHousenumber() : updateOrder.getNeighborhood());
            updateOrder.setCep(numberOrder.getCustomerAddressRequest().getCep() != updateOrder.getNeighborhood() ? numberOrder.getCustomerAddressRequest().getNeighborhood() : updateOrder.getNeighborhood());
            updateOrder.setRoad(numberOrder.getCustomerAddressRequest().getRoad() != updateOrder.getNeighborhood() ? numberOrder.getCustomerAddressRequest().getRoad() : updateOrder.getRoad());

            logger.error("Atualização do endereço feita com sucesso!");
            return ResponseEntity.ok(orderAddressRepository.save(updateOrder));
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

    public ResponseEntity<OrderEntity> cancelOrder(OrderRequest numberOrder) {
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
