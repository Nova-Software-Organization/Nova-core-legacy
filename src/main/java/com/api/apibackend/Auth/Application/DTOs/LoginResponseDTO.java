package com.api.apibackend.Auth.Application.DTOs;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}