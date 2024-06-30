/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.DeliveryService.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apibackend.modules.DeliveryService.infra.persistence.entity.DeliveryServiceEntity;

public interface DeliveryServiceRepository extends JpaRepository<DeliveryServiceEntity, Long> { }
