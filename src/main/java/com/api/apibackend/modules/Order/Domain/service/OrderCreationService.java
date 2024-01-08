/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Order.Domain.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Domain.service.CustomerOrderService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.modules.CustomerAddress.Domain.service.CustomerAddressOrderService;
import com.api.apibackend.modules.MovementStock.Infra.persistence.entity.StockMovementEntity;
import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.Domain.event.OrderCreatedEvent;
import com.api.apibackend.modules.Order.Domain.exception.InsufficientStockException;
import com.api.apibackend.modules.Order.Domain.exception.OrderCannotBeCreatedException;
import com.api.apibackend.modules.Order.Domain.repository.IOrderCreationService;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;
import com.api.apibackend.modules.OrderItem.Domain.exception.NonExistentesItemsException;
import com.api.apibackend.modules.OrderItem.Domain.service.OrderItemCreationService;
import com.api.apibackend.modules.OrderItem.infra.persistence.entity.OrderItemEntity;
import com.api.apibackend.modules.OrderItem.infra.persistence.repository.OrderItemRepository;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.repository.ProductRepository;
import com.api.apibackend.modules.Stock.Infra.persistence.entity.StockEntity;
import com.api.apibackend.modules.Stock.Infra.persistence.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderCreationService implements IOrderCreationService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository clientRepository;
    private OrderItemRepository orderItemRepository;
    private OrderItemCreationService orderItemCreationService;
    private CustomerFilterService customerSearchService;
    private CustomerOrderService customerOrderService;
    private CustomerAddressOrderService customerAddressOrderService;
    private ApplicationEventPublisher eventPublisher;
    private StockRepository stockRepository;

    @Autowired
    public OrderCreationService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CustomerRepository clientRepository,
            OrderItemRepository orderItemRepository,
            OrderItemCreationService orderItemCreationService,
            CustomerFilterService customerSearchService,
            CustomerOrderService customerOrderService,
            CustomerAddressOrderService customerAddressOrderService,
            ApplicationEventPublisher eventPublisher,
            StockRepository stockRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemCreationService = orderItemCreationService;
        this.customerSearchService = customerSearchService;
        this.customerOrderService = customerOrderService;
        this.customerAddressOrderService = customerAddressOrderService;
        this.eventPublisher = eventPublisher;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public ResponseEntity<String> createOrder(OrderRequest orderRequest, CustomerAddressRequest customerAddress,
            ClientRequest clientRequest)
            throws InsufficientStockException, OrderCannotBeCreatedException, NonExistentesItemsException {
        validateOrderRequest(orderRequest);

        OrderEntity newOrder = createOrderEntity(orderRequest, customerAddress, clientRequest);
        updateClientAndAddress(newOrder, clientRequest, customerAddress);
        List<OrderItemEntity> orderItems = orderItemCreationService.createOrderItems(orderRequest.getItems(), newOrder);

        saveOrderAndItems(newOrder, orderItems);
        finalizeOrder(orderItems);

        OrderCreatedEvent orderCreated = new OrderCreatedEvent(this, newOrder.getNumberOrder());
        eventPublisher.publishEvent(orderCreated);

        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido Criado com sucesso");
    }

    private void validateOrderRequest(OrderRequest orderRequest) throws OrderCannotBeCreatedException {
        if (orderRequest == null) {
            throw new OrderCannotBeCreatedException("Pedido não pode ser criado, objeto inválido");
        }
    }

    @Override
    public OrderEntity createOrderEntity(OrderRequest orderRequest, CustomerAddressRequest customerAddress,
            ClientRequest clientRequest) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setStatus("Aguardando confirmação de pagamento");
        orderEntity.setCustomerEmail(orderRequest.getCustomerEmail());
        orderEntity.setDatePayment(new Date());
        orderEntity.setPaymentMethod(orderRequest.getPaymentMethod());

        return orderEntity;
    }

    @Override
    public void updateClientAndAddress(OrderEntity orderEntity, ClientRequest clientRequest,
            CustomerAddressRequest customerAddress) {
        Objects.requireNonNull(orderEntity, "A entidade de pedido não pode ser nulo");
        Objects.requireNonNull(clientRequest, "O pedido do cliente não pode ser nulo");
        Objects.requireNonNull(customerAddress, "O endereço do cliente não pode ser nulo");

        CustomerEntity existingClient = customerSearchService.findExistingClient(clientRequest);

        if (existingClient == null) {
            createAndSaveNewClient(orderEntity, clientRequest);
        } else {
            updateExistingClient(orderEntity, existingClient, customerAddress);
        }
    }

    @Override
    public void createAndSaveNewClient(OrderEntity orderEntity, ClientRequest clientRequest) {
        CustomerEntity newClient = customerOrderService.createNewCustomerOrder(clientRequest);
        clientRepository.save(newClient);
        orderEntity.setClient(newClient);
    }

    @Override
    public void updateExistingClient(OrderEntity orderEntity, CustomerEntity existingClient,
            CustomerAddressRequest customerAddress) {
        orderEntity.setClient(existingClient);
        
        OrderAddressEntity newAddress = createOrderAddressEntity(customerAddress);
        orderEntity.setOrderAddressEntity(newAddress);
    }

    public OrderAddressEntity createOrderAddressEntity(CustomerAddressRequest customerAddress) {
        OrderAddressEntity orderAddressEntity = new OrderAddressEntity();
    
        orderAddressEntity.setRoad(customerAddress.getRoad());
        orderAddressEntity.setNeighborhood(customerAddress.getNeighborhood());
        orderAddressEntity.setHousenumber(customerAddress.getHousenumber());
        orderAddressEntity.setCep(customerAddress.getCep());
    
        return orderAddressEntity;
    }

    @Override
    public void saveOrderAndItems(OrderEntity orderEntity, List<OrderItemEntity> orderItems) {
        orderEntity.setCustomerName(orderEntity.getClient().getName());
        orderEntity.setProducts(orderItems);
        orderEntity.calculateTotal();

        orderEntity = orderRepository.save(orderEntity);
        orderItemRepository.saveAll(orderItems);
    }

    @Override
    public void finalizeOrder(List<OrderItemEntity> orderItems) throws InsufficientStockException {
        Map<StockEntity, Integer> stockUpdates = new HashMap<>();

        for (OrderItemEntity orderItem : orderItems) {
            updateProductStock(orderItem, stockUpdates);
        }

        // Aplicar todas as atualizações em uma única transação
        applyStockUpdates(stockUpdates);
    }

    public void updateProductStock(OrderItemEntity orderItem, Map<StockEntity, Integer> stockUpdates)
            throws InsufficientStockException {
        ProductEntity productEntity = orderItem.getProduct();
        StockEntity stockEntity = productEntity.getStockEntity();

        int currentStock = stockEntity.getInput_quantity() - stockEntity.getOutput_quantity();
        int requestedQuantity = orderItem.getQuantity();

        if (currentStock < requestedQuantity) {
            throw new InsufficientStockException("Estoque insuficiente para o produto " + productEntity.getIdProduct());
        }

        stockEntity.setOutput_quantity(stockEntity.getOutput_quantity() + requestedQuantity);

        // Capture stock movement details
        StockMovementEntity movement = new StockMovementEntity(requestedQuantity, new Date());
        stockEntity.getMovements().add(movement);

        stockUpdates.put(stockEntity, currentStock - requestedQuantity);
    }

    public void applyStockUpdates(Map<StockEntity, Integer> stockUpdates) {
        stockUpdates.forEach((stockEntity, newStock) -> {
            stockEntity.setInput_quantity(newStock);
            stockRepository.save(stockEntity);
        });
    }
}
