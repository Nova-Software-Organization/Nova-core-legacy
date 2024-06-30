package com.api.apibackend.modules.Cart.Domain.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Cart.Application.services.IDiscountService;
import com.api.apibackend.modules.Coupon.Infra.entity.CouponEntity;
import com.api.apibackend.modules.Coupon.Infra.repository.CouponRepository;
import com.api.apibackend.shared.error.exception.InvalidArgumentException;

@Service
public class DiscountServiceImpl implements IDiscountService {
    private final CouponRepository couponRepository;
    private final CartServiceImpl cartService;

    @Autowired
    public DiscountServiceImpl(CouponRepository discountRepository, CartServiceImpl cartService) {
        this.couponRepository = discountRepository;
        this.cartService = cartService;
    }

    @Override
    public void applyDiscount(String code) {
        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponCode(code);

        if (!optionalCoupon.isPresent()) {
            throw new InvalidArgumentException("Discount code does not exist!");
        }

        CouponEntity coupon = optionalCoupon.get();
        if (coupon.getExpirationDate().isBefore(LocalDate.now())) {
            throw new InvalidArgumentException("Discount code is expired!");
        }
    }
}
