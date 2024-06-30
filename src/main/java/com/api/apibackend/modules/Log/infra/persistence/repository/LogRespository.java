/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Log.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apibackend.modules.Log.infra.persistence.entity.LogEntity;

public interface LogRespository  extends JpaRepository<LogEntity, Long> { }
