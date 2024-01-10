package com.api.apibackend.modules.Auth.Application.DTOs.mail;

import lombok.Data;

@Data
public class AuthUserResetPassawordDTO {
    private String username;
    private String email;
}
