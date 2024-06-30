package com.api.apibackend.modules.Auth.Application.services;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.token.TokenResetPasswordDTO;

public interface IRedefinePasswordService {
   ResponseEntity<ResponseMessageDTO> execute(TokenResetPasswordDTO tokenResetPasswordDTO);
}
