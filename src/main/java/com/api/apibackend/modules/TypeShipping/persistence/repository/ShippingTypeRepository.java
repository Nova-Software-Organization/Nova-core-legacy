/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.TypeShipping.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.TypeShipping.persistence.entity.ShippingTypeEntity;

@Repository
public interface ShippingTypeRepository extends JpaRepository<ShippingTypeEntity, Long> {}
