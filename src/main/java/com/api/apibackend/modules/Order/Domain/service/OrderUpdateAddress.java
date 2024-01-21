package com.api.apibackend.modules.Order.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.repository.OrderAddressRepository;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderUpdateAddress {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderAddressRepository orderAddressRepository;

    @Autowired
    public OrderUpdateAddress(OrderRepository orderRepository, ProductRepository productRepository, OrderAddressRepository orderAddressRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderAddressRepository = orderAddressRepository;
    }

    public ResponseEntity<OrderAddressEntity> updateAddressOrder(OrderUpdateAddressRequest numberOrder) {
        Optional<OrderEntity> order = orderRepository.findById(numberOrder.getNumberOrder());

        if (order.isPresent()) {
            OrderAddressEntity updateOrder = order.get().getOrderAddressEntity();

            updateOrder.setNeighborhood(
                    numberOrder.getCustomerAddressRequest().getNeighborhood() != updateOrder.getNeighborhood()
                            ? numberOrder.getCustomerAddressRequest().getNeighborhood()
                            : updateOrder.getNeighborhood());
            updateOrder.setHousenumber(
                    numberOrder.getCustomerAddressRequest().getHousenumber() != updateOrder.getHousenumber()
                            ? numberOrder.getCustomerAddressRequest().getHousenumber()
                            : updateOrder.getNeighborhood());
            updateOrder.setCep(numberOrder.getCustomerAddressRequest().getCep() != updateOrder.getNeighborhood()
                    ? numberOrder.getCustomerAddressRequest().getNeighborhood()
                    : updateOrder.getNeighborhood());
            updateOrder.setRoad(numberOrder.getCustomerAddressRequest().getRoad() != updateOrder.getNeighborhood()
                    ? numberOrder.getCustomerAddressRequest().getRoad()
                    : updateOrder.getRoad());

            log.error("Atualização do endereço feita com sucesso!");
            return ResponseEntity.ok(orderAddressRepository.save(updateOrder));
        }

        log.error("Atualização do endereço não pode ser feita!, endereço não encontrado");
        return ResponseEntity.notFound().build();
    }
}
