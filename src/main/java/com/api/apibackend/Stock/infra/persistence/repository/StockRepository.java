package com.api.apibackend.Stock.infra.persistence.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Stock.infra.persistence.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> { }
