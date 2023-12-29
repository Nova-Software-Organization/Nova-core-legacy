package com.api.apibackend.Unity.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Unity.infra.persistence.entity.UnityEntity;

@Repository
public interface UnityRepository extends JpaRepository<UnityEntity, Long>{ }
