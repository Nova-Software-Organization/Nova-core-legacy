package com.api.apibackend.modules.Color.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.Color.infra.persistence.entity.ColorEntity;


@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long> { }
