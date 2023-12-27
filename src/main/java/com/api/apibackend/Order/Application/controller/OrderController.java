package com.api.apibackend.Order.Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Order.Application.DTOs.CreateOrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.Order.Application.repository.IOrderController;
import com.api.apibackend.Order.Application.useCase.OrderAddressUpdate;
import com.api.apibackend.Order.Application.useCase.OrderCancelOrder;
import com.api.apibackend.Order.Application.useCase.OrderRequestManagerUseCase;
import com.api.apibackend.Order.Domain.service.OrderCompletionReturnProcessor;
import com.api.apibackend.Order.Domain.service.OrderCreationService;
import com.api.apibackend.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Order.infra.service.OrderCircuitBreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/pedido")
public class OrderController implements IOrderController {
    private OrderCreationService orderService;
    private OrderRequestManagerUseCase orderManageUseCase;
    private UpdateOrderService updateOrderService;
    private OrderCompletionReturnProcessor orderCompletionReturnProcessor;
    private OrderCircuitBreaker orderCircuitBreaker;
    private OrderAddressUpdate orderAddressUpdate;
    private OrderCancelOrder orderCancelOrder;

    @Autowired
    public OrderController(
            OrderCreationService orderService,
            OrderRequestManagerUseCase orderManageUseCase,
            UpdateOrderService updateOrderService,
            OrderCompletionReturnProcessor orderCompletionReturnProcessor,
            OrderCircuitBreaker orderCircuitBreaker,
            OrderAddressUpdate orderAddressUpdate,
            OrderCancelOrder orderCancelOrder
    ) {
        this.orderService = orderService;
        this.orderManageUseCase = orderManageUseCase;
        this.updateOrderService = updateOrderService;
        this.orderCompletionReturnProcessor = orderCompletionReturnProcessor;
        this.orderCircuitBreaker = orderCircuitBreaker;
        this.orderAddressUpdate = orderAddressUpdate;
        this.orderCancelOrder = orderCancelOrder;
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Lista todos os pedidos", description = "Responsavel por listar todos os pedidos que estão dentro do banco de dados")
    @Operation(summary = "Efetua um listagem de todos os pedidos que estão dentro do banco de dados!")
    public ResponseEntity<?> listOrders() {
        try {
            ResponseEntity<List<OrderEntity>> orders = orderCompletionReturnProcessor.getOrderList();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @PostMapping("/criar/pedido")
    @CircuitBreaker(name = "criarpedido", fallbackMethod = "fallbackCreateOrder")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "criar pedido", description = "Responsavel por criar um pedido feito pelo cliente")
    @Operation(summary = "Rota tem como objetivo criar fazer o pedido do cliente e validar todas as situações possiveis antes de contabilizar no sistema a compra!")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderCircuitBreaker.executeCreateOrder(createOrderRequest, () -> {
            ResponseEntity<String> orderResponse = orderManageUseCase.executeRequestManage(createOrderRequest);

            if (orderResponse.getStatusCode().is2xxSuccessful()) {
                return new ResponseEntity<>(orderResponse.getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(orderResponse.getBody(), orderResponse.getStatusCode());
            }
        });
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Retorna o pedido", description = "Responsavel por retornar um pedido")
    @Operation(summary = "Efetuar uma busca pelo pedido por id e retorna-lo quando possivel!")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        try {
            ResponseEntity<Optional<OrderEntity>> order = orderCompletionReturnProcessor.getOrderId(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Atualiza o pedido", description = "Atualiza um pedido pelo id informado")
    @Operation(summary = "Efetua a busca pelo pedido informado e atualiza o mesmo")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> updatedOrder = updateOrderService.updateOrder(id, orderRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Tag(name = "Deleta um pedido do banco de dados", description = "Responsavel por deletar um pedido do banco de dados")
    @Operation(summary = "Efetua a busca pelo id informado e deleta o pedido")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            updateOrderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @PostMapping("/cancelamento")
    @CircuitBreaker(name = "cancelamentopedido", fallbackMethod = "")
    @PreAuthorize("hasRole('ADMIN')")
    @Tag(name = "Cancelamento do pedido", description = "Efetua o cancelamento do pedido")
    @Operation(summary = "Busca pelo id do pedido informado e efetua o cancelamento alterando o status a dando um break nos outros processos")
    public ResponseEntity<?> canceladOrder(@RequestBody OrderRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> canceledOrder = orderCancelOrder.execute(orderRequest);
            return new ResponseEntity<>(canceledOrder, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @PostMapping("/atualizar/endereco")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Atualiza o endereço do pedido", description = "Atualiza o endereço do pedido")
    @Operation(summary = "efetua uma alteração no endereço do pedido informado")
    public ResponseEntity<?> updateOrderAddress(@RequestBody OrderUpdateAddressRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> updatedOrder = orderAddressUpdate.execute(orderRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}
