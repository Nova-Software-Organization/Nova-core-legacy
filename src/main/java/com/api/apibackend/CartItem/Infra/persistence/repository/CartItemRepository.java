package com.api.apibackend.CartItem.Infra.persistence.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.OrderItem.infra.persistence.entity.OrderItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<OrderItemEntity, Long> { }
