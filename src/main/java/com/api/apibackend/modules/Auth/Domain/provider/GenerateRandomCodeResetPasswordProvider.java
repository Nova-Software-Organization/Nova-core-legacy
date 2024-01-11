package com.api.apibackend.modules.Auth.Domain.provider;

import java.util.Random;

public class GenerateRandomCodeResetPasswordProvider {
    public String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
