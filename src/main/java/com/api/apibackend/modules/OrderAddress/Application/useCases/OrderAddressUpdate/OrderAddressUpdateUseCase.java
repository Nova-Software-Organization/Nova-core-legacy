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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.modules.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.modules.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

@Service
public class OrderAddressUpdateUseCase {
    private UpdateOrderService updateOrderService;

    @Autowired
    public OrderAddressUpdateUseCase(UpdateOrderService updateOrderService) {
        this.updateOrderService = updateOrderService;
    }

    public ResponseEntity<OrderAddressEntity> execute(OrderUpdateAddressRequest updateAddressRequest) {
        if (updateAddressRequest != null) {
            return updateOrderService.updateAddressOrder(updateAddressRequest);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitação de atualização de endereço inválida");
    }
}
