package com.api.apibackend.Unity.infra.persistence.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Unity.infra.persistence.entity.UnityEntity;

@Repository
public interface UnityRepository extends JpaRepository<UnityEntity, Long>{ }
