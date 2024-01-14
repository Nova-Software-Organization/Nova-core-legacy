package com.api.apibackend.modules.Auth.Application.DTOs.token;

import lombok.Data;

@Data
public class TokenResetPasswordDTO {
    private String tokenPassword;
    private String newPassword;
}