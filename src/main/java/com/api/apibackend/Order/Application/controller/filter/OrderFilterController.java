package com.api.apibackend.Order.Application.controller.filter;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Domain.service.OrderCompletionReturnProcessor;
import com.api.apibackend.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/pedido/filtro")
public class OrderFilterController {
    private OrderCompletionReturnProcessor orderCompletionReturnProcessor;
    private UpdateOrderService updateOrderService;
    
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

}
