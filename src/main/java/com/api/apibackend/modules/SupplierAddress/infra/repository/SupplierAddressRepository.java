/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.SupplierAddress.infra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

@Repository
public interface SupplierAddressRepository extends JpaRepository<SupplierAddressEntity, Long> {
    Optional<SupplierAddressEntity> findByRoadAndNeighborhoodAndNumberCompanyAndCep(
            String road, String neighborhood, String numberHouseOrCompany, String cep);
    List<SupplierAddressEntity> findByCep(String cep);
    List<SupplierAddressEntity> findByNumberCompany(String numberCompany);
    List<SupplierAddressEntity> findByNeighborhood(String neighborhood);
    List<SupplierAddressEntity> findByRoad(String road);
}
