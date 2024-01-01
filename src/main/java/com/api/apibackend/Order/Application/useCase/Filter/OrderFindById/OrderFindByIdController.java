/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Order.Application.useCase.Filter.OrderFindById;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Order.Domain.service.OrderFindById;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/pedido")
public class OrderFindByIdController {
    private OrderFindById orderFindById;

    @GetMapping("/retorna/{id}")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Retorna o pedido", description = "Responsavel por retornar um pedido")
    @Operation(summary = "Efetuar uma busca pelo pedido por id e retorna-lo quando possivel!")
    public ResponseEntity<?> handle(@PathVariable Long id) {
        try {
            ResponseEntity<Optional<OrderEntity>> order = orderFindById.getOrderId(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na solicitação: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}
