package com.api.apibackend.PriceUnitary.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apibackend.PriceUnitary.Infra.persistence.entity.PriceUnitaryEntity;

public interface PriceRepository extends JpaRepository<PriceUnitaryEntity, Long>{ }
