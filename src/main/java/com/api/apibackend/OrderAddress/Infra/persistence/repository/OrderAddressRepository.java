package com.api.apibackend.OrderAddress.Infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddressEntity, Long> {
    
}
