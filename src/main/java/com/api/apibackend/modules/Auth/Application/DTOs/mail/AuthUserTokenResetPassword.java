package com.api.apibackend.modules.Auth.Application.DTOs.mail;

import lombok.Data;

@Data
public class AuthUserTokenResetPassword {
    private String tokenGenerate;
    private String newPassword;
}
