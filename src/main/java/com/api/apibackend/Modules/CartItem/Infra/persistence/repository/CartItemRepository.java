/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.CartItem.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Modules.OrderItem.infra.persistence.entity.OrderItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<OrderItemEntity, Long> { }
