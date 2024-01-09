package com.api.apibackend.modules.Supplier.Infra.persistence.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{ }
