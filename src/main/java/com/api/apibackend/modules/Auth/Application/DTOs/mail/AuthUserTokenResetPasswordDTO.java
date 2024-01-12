package com.api.apibackend.modules.Auth.Application.DTOs.mail;

import lombok.Data;

@Data
public class AuthUserTokenResetPasswordDTO {
    private String tokenGenerate;
    private String newPassword;
}
