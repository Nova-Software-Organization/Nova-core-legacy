package com.api.apibackend.Coupon.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Coupon.infra.entity.CouponEntity;

@Repository
public interface CouponRepository extends JpaRepository<Long, CouponEntity> {

}
