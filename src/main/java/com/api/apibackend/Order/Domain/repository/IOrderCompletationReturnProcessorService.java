package com.api.apibackend.Order.Domain.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

public interface IOrderCompletationReturnProcessorService {
    public ResponseEntity<List<OrderEntity>> getOrderList();

    public ResponseEntity<Optional<OrderEntity>> getOrderId(Long idOrder);
}
