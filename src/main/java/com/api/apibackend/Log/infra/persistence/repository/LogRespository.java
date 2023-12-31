package com.api.apibackend.Log.infra.persistence.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apibackend.Log.infra.persistence.entity.LogEntity;

public interface LogRespository  extends JpaRepository<LogEntity, Long> { }
