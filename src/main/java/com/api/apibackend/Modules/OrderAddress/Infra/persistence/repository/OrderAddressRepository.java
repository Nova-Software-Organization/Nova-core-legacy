/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.OrderAddress.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddressEntity, Long> {
    
}
