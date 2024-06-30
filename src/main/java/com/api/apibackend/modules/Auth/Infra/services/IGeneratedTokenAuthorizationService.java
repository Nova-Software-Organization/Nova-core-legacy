package com.api.apibackend.modules.Auth.Infra.services;

import java.util.Date;
import java.util.Set;

import com.api.apibackend.modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.modules.Auth.Domain.exception.GenerateTokenErrorException;
import com.api.apibackend.modules.Auth.Domain.exception.InvalidGenerateTokenException;

public interface IGeneratedTokenAuthorizationService {
  String generateToken(String username, Set<CustomGrantedAuthority> customGrantedAuthorities) throws GenerateTokenErrorException;
  String getSubject(String tokenJWT) throws InvalidGenerateTokenException;
  Date dateExpiration();
}
