package com.api.apibackend.Log.infra.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.apibackend.Log.infra.persistence.entity.LogEntity;

public interface LogRespository  extends JpaRepository<LogEntity, Long> { }
