/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Order.Domain.repository;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.Domain.exception.InsufficientStockException;
import com.api.apibackend.modules.Order.Domain.exception.OrderCannotBeCreatedException;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.OrderItem.Domain.exception.NonExistentesItemsException;
import com.api.apibackend.modules.OrderItem.infra.persistence.entity.OrderItemEntity;
import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;

public interface IOrderCreationService {
    ResponseEntity<String> createOrder(OrderRequest orderRequest, CustomerAddressRequest customerAddress,
    ClientRequest clientRequest) throws InsufficientStockException, OrderCannotBeCreatedException, NonExistentesItemsException;
    OrderEntity createOrderEntity(OrderRequest orderRequest, CustomerAddressRequest customerAddress, ClientRequest clientRequest);
    void updateClientAndAddress(OrderEntity orderEntity, ClientRequest clientRequest, CustomerAddressRequest customerAddress);
    void createAndSaveNewClient(OrderEntity orderEntity, ClientRequest clientRequest);
    void updateExistingClient(OrderEntity orderEntity, CustomerEntity existingClient, CustomerAddressRequest customerAddress);
    void saveOrderAndItems(OrderEntity orderEntity, List<OrderItemEntity> orderItems);
    void finalizeOrder(List<OrderItemEntity> orderItems) throws InsufficientStockException;
    public void updateProductStock(OrderItemEntity orderItem, Map<StockEntity, Integer> stockUpdates) throws InsufficientStockException;
    void applyStockUpdates(Map<StockEntity, Integer> stockUpdates);
}
