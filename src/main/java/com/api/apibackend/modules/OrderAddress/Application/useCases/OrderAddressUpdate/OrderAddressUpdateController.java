/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.OrderAddress.Application.useCases.OrderAddressUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.modules.Order.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/pedido")
public class OrderAddressUpdateController {
    private OrderAddressUpdateUseCase orderAddressUpdateUseCase;

    @Autowired
    public OrderAddressUpdateController(OrderAddressUpdateUseCase orderAddressUpdateUseCase) {
        this.orderAddressUpdateUseCase = orderAddressUpdateUseCase;
    }

    @PostMapping("/atualizar/endereco")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "Atualiza o endereço do pedido", description = "Atualiza o endereço do pedido")
    @Operation(summary = "efetua uma alteração no endereço do pedido informado")
    public ResponseEntity<ResponseMessageDTO> updateOrderAddress(@RequestBody OrderUpdateAddressRequest orderRequest) {
        try {
            ResponseEntity<OrderAddressEntity> updatedOrder = orderAddressUpdateUseCase.execute(orderRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessageDTO("pedido atualizado com sucesso!", null, null, updatedOrder,
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
