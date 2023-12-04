package com.api.apibackend.Order.Application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Order.Application.repository.IOrderController;
import com.api.apibackend.Order.Application.useCase.OrderUseCase;
import com.api.apibackend.Order.Domain.model.CreateOrderRequest;
import com.api.apibackend.Order.Domain.service.OrderCompletionReturnProcessor;
import com.api.apibackend.Order.Domain.service.OrderCreationService;
import com.api.apibackend.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Order.infra.entity.OrderEntity;
import com.api.apibackend.Order.infra.service.OrderCircuitBreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/v1/pedido")
public class OrderController implements IOrderController {

    private OrderCreationService orderService;
    private OrderUseCase orderManageUseCase;
	private UpdateOrderService updateOrderService;
    private OrderCompletionReturnProcessor orderCompletionReturnProcessor;
    private OrderCircuitBreaker orderCircuitBreaker;

    @Autowired
    public OrderController(
        OrderCreationService orderService,
        OrderUseCase orderManageUseCase,
        UpdateOrderService updateOrderService,
        OrderCompletionReturnProcessor orderCompletionReturnProcessor,
        OrderCircuitBreaker orderCircuitBreaker
    ) {
        this.orderService = orderService;
        this.orderManageUseCase = orderManageUseCase;
        this.updateOrderService = updateOrderService;
        this.orderCompletionReturnProcessor = orderCompletionReturnProcessor;
        this.orderCircuitBreaker = orderCircuitBreaker;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listOrders() {
        try {
            ResponseEntity<List<OrderEntity>> orders = orderCompletionReturnProcessor.getOrderList();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }

	@PostMapping("/criar")
	@CircuitBreaker(name = "criarpedido", fallbackMethod = "fallbackCreateOrder")
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
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        try {
            ResponseEntity<Optional<OrderEntity>> order = orderCompletionReturnProcessor.getOrderId(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> updatedOrder = updateOrderService.updateOrder(id, orderRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            updateOrderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }

	@PostMapping("/cancelamento")
    @CircuitBreaker(name = "cancelamentopedido", fallbackMethod = "")
    public ResponseEntity<?> canceladOrder(@RequestBody OrderRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> canceledOrder = orderManageUseCase.executeCanceladOrder(orderRequest);
            return new ResponseEntity<>(canceledOrder, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }

    @PostMapping("/atualizar/endereco")
    public ResponseEntity<?> updateOrderAddress(@RequestBody OrderRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> updatedOrder = orderManageUseCase.executeAddress(orderRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}


