/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Order.Application.useCase.OrderCancel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/pedido")
public class OrderCanceledController {
    private OrderCancelOrderUseCase orderCancelOrderUseCase;

    @PostMapping("/cancelamento")
    @CircuitBreaker(name = "cancelamentopedido", fallbackMethod = "")
    @PreAuthorize("hasRole('ADMIN')")
    @Tag(name = "Cancelamento do pedido", description = "Efetua o cancelamento do pedido")
    @Operation(summary = "Busca pelo id do pedido informado e efetua o cancelamento alterando o status a dando um break nos outros processos")
    public ResponseEntity<ResponseMessageDTO> handle(@RequestBody OrderRequest orderRequest) {
        try {
            ResponseEntity<OrderEntity> canceledOrder = orderCancelOrderUseCase.execute(orderRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessageDTO("pedido cancelado com sucesso!", canceledOrder,
                            this.getClass().getName(), null));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("erro na solicitação: ", this.getClass().getName(), ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição!",
                            this.getClass().getName(), ex.getMessage()));
        }
    }
}
