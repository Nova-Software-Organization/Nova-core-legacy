package com.api.apibackend.Order.Domain.repository;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;
import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Domain.exception.InsufficientStockException;
import com.api.apibackend.Order.Domain.exception.OrderCannotBeCreatedException;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.OrderItem.Domain.exception.NonExistentesItemsException;
import com.api.apibackend.OrderItem.infra.entity.OrderItemEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;

public interface IOrderCreationService {
    ResponseEntity<String> createOrder(OrderRequest orderRequest, CustomerAddressRequest customerAddress,
            ClientRequest clientRequest) throws InsufficientStockException, OrderCannotBeCreatedException, NonExistentesItemsException;
    OrderEntity createOrderEntity(OrderRequest orderRequest, CustomerAddressRequest customerAddress, ClientRequest clientRequest);
    void updateClientAndAddress(OrderEntity orderEntity, ClientRequest clientRequest, CustomerAddressRequest customerAddress);
    void createAndSaveNewClient(OrderEntity orderEntity, ClientRequest clientRequest);
    void updateExistingClient(OrderEntity orderEntity, CustomerEntity existingClient, CustomerAddressRequest customerAddress);
    void updateOrderEntityWithNewAddress(OrderEntity orderEntity, AddressEntity newAddress);
    void saveOrderAndItems(OrderEntity orderEntity, List<OrderItemEntity> orderItems);
    void finalizeOrder(List<OrderItemEntity> orderItems) throws InsufficientStockException;
    void updateProductStock(OrderItemEntity orderItem, Map<ProductEntity, Integer> stockUpdates) throws InsufficientStockException;
    void applyStockUpdates(Map<ProductEntity, Integer> stockUpdates);
}
