package com.api.apibackend.modules.Auth.Infra.services;

import com.api.apibackend.modules.Auth.Domain.model.PasswordResetToken;

public interface ITokenGeneratedResetPasswordService {
  static PasswordResetToken generateTokenWithExpiration() {
    return new PasswordResetToken(null, null);
  }
}
