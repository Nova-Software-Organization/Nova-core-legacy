package com.api.apibackend.modules.Auth.Application.services;

import com.api.apibackend.modules.Auth.Application.DTOs.token.TokenResetPasswordDTO;

public interface IResetPasswordValidateService {
  boolean validateToken(TokenResetPasswordDTO tokenResetPasswordDTO);
}
