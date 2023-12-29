package com.api.apibackend.PriceUnitary.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.PriceUnitary.Infra.persistence.entity.PriceUnitaryEntity;

@Repository
public interface PriceUnitaryRepository extends JpaRepository<PriceUnitaryEntity, Long> {
}
