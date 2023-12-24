package com.api.apibackend.Auth.Application.DTOs;

import lombok.Data;

@Data
public class ValidateTokenRequest {
    private String token;
}
