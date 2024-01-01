/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.SupplierAddress.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;
import java.util.List;

@Repository
public interface SupplierAddressRepository extends JpaRepository<SupplierAddressEntity, Long> {
    Optional<SupplierAddressEntity> findByRoadAndNeighborhoodAndNumberHouseOrCompanyAndCep(
            String road, String neighborhood, String numberHouseOrCompany, String cep);
}
