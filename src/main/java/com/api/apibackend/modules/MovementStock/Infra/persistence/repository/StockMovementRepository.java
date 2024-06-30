/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.MovementStock.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.MovementStock.Infra.persistence.entity.StockMovementEntity;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovementEntity, Long> {
    
}
