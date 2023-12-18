package com.api.apibackend.Price.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apibackend.Price.infra.entity.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> { }
