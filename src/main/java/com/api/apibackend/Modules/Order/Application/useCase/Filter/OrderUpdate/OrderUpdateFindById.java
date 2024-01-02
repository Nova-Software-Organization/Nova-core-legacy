/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Order.Application.useCase.Filter.OrderUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Modules.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/pedido")
public class OrderUpdateFindById {
    private UpdateOrderService updateOrderService;

    @Autowired
    public OrderUpdateFindById(UpdateOrderService updateOrderService) {
        this.updateOrderService = updateOrderService;
    }

    @PutMapping("/atualizar/{id}")
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
}
