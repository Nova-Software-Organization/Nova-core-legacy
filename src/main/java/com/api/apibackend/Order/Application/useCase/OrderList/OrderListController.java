/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Order.Application.useCase.OrderList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Order.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Order.Domain.service.OrderListService;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/pedido")
public class OrderListController {
    private OrderListService orderListService;

    @Autowired
    public OrderListController(OrderListService orderListService) {
        this.orderListService = orderListService;
    }

    @GetMapping("/listar")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Lista todos os pedidos", description = "Responsavel por listar todos os pedidos que estão dentro do banco de dados")
    @Operation(summary = "Efetua um listagem de todos os pedidos que estão dentro do banco de dados!")
    public ResponseEntity<ResponseMessageDTO> handle() {
        try {
            ResponseEntity<List<OrderEntity>> orders = orderListService.getOrderList();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessageDTO(orders, "lista retornada com sucesso!", this.getClass().getName(), null));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "Erro ao processar a requisição!", this.getClass().getName(), ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição!",
                            this.getClass().getName(), ex.getMessage()));
        }
    }
}
