/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Order.Domain.repository;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.Modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Modules.Order.Domain.exception.InsufficientStockException;
import com.api.apibackend.Modules.Order.Domain.exception.OrderCannotBeCreatedException;
import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Modules.OrderItem.Domain.exception.NonExistentesItemsException;
import com.api.apibackend.Modules.OrderItem.infra.persistence.entity.OrderItemEntity;
import com.api.apibackend.Modules.Stock.Infra.persistence.entity.StockEntity;

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
