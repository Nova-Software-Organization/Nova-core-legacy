package com.api.apibackend.MovementStock.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.MovementStock.Infra.persistence.entity.StockMovementEntity;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovementEntity, Long> {
    
}
