package com.api.apibackend.Order.Domain.service;

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

import com.api.apibackend.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.Customer.Domain.service.CustomerOrderService;
import com.api.apibackend.Customer.Domain.service.CustomerSearchService;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.CustomerAddress.Domain.service.CustomerAddressOrderService;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;
import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Domain.event.OrderCreatedEvent;
import com.api.apibackend.Order.Domain.exception.InsufficientStockException;
import com.api.apibackend.Order.Domain.exception.OrderCannotBeCreated;
import com.api.apibackend.Order.Domain.repository.IOrderCreationService;
import com.api.apibackend.Order.infra.entity.OrderEntity;
import com.api.apibackend.Order.infra.repository.OrderRepository;
import com.api.apibackend.OrderItem.Domain.service.OrderItemCreationService;
import com.api.apibackend.OrderItem.infra.entity.OrderItemEntity;
import com.api.apibackend.OrderItem.infra.repository.OrderItemRepository;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderCreationService implements IOrderCreationService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository clientRepository;
    private OrderItemRepository orderItemRepository;
    private OrderItemCreationService orderItemCreationService;
    private CustomerSearchService customerSearchService;
    private CustomerOrderService customerOrderService;
    private CustomerAddressOrderService customerAddressOrderService;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderCreationService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CustomerRepository clientRepository,
            OrderItemRepository orderItemRepository,
            OrderItemCreationService orderItemCreationService,
            CustomerSearchService customerSearchService,
            CustomerOrderService customerOrderService,
            CustomerAddressOrderService customerAddressOrderService,
            ApplicationEventPublisher eventPublisher
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemCreationService = orderItemCreationService;
        this.customerSearchService = customerSearchService;
        this.customerOrderService = customerOrderService;
        this.customerAddressOrderService = customerAddressOrderService;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public ResponseEntity<String> createOrder(OrderRequest orderRequest, CustomerAddressRequest customerAddress,
            ClientRequest clientRequest) throws InsufficientStockException, OrderCannotBeCreated {
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

    private void validateOrderRequest(OrderRequest orderRequest) throws OrderCannotBeCreated {
        if (orderRequest == null) {
            throw new OrderCannotBeCreated("Pedido não pode ser criado, objeto inválido");
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
        AddressEntity existingAddress = existingClient.getAddress();
        AddressEntity newAddress = customerAddressOrderService.createAddressOrder(customerAddress);

        if (!existingAddress.isSameAddress(newAddress)) {
            existingClient.setAddress(newAddress);
            updateOrderEntityWithNewAddress(orderEntity, newAddress);
        }
    }

    @Override
    public void updateOrderEntityWithNewAddress(OrderEntity orderEntity, AddressEntity newAddress) {
        orderEntity.setRoad(newAddress.getRoad());
        orderEntity.setNeighborhood(newAddress.getNeighborhood());
        orderEntity.setHousenumber(newAddress.getHousenumber());
        orderEntity.setCep(newAddress.getCep());
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
        Map<ProductEntity, Integer> stockUpdates = new HashMap<>();

        for (OrderItemEntity orderItem : orderItems) {
            updateProductStock(orderItem, stockUpdates);
        }

        // Aplicar todas as atualizações em uma única transação
        applyStockUpdates(stockUpdates);
    }

    public void updateProductStock(OrderItemEntity orderItem, Map<ProductEntity, Integer> stockUpdates)
            throws InsufficientStockException {
        ProductEntity productEntity = orderItem.getProduct();
        int currentStock = productEntity.getQuantityInStock();
        int requestedQuantity = orderItem.getQuantity();

        if (currentStock < requestedQuantity) {
            throw new InsufficientStockException("Estoque insuficiente para o produto " + productEntity.getIdProduct());
        }

        int newStock = currentStock - requestedQuantity;
        stockUpdates.put(productEntity, newStock);
    }

    public void applyStockUpdates(Map<ProductEntity, Integer> stockUpdates) {
        stockUpdates.forEach((productEntity, newStock) -> {
            productEntity.setQuantityInStock(newStock);
            productRepository.save(productEntity);
        });
    }
}
