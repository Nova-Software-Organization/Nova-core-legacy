package com.api.apibackend.modules.Coupon.Domain.provider;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateCouponProvider {
    public String generateRandomCode() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }
}
