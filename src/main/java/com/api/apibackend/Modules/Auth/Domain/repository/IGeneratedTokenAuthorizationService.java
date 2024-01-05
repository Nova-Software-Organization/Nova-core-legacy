/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Auth.Domain.repository;

import java.util.Date;
import java.util.Set;

import com.api.apibackend.Modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Modules.Auth.Domain.exception.GenerateTokenErrorException;
import com.api.apibackend.Modules.Auth.Domain.exception.InvalidGenerateTokenException;

public interface IGeneratedTokenAuthorizationService {
    String generateToken(String username, Set<CustomGrantedAuthority> customGrantedAuthorities) throws GenerateTokenErrorException;
    String getSubject(String tokenJWT) throws InvalidGenerateTokenException;
    Date dateExpiration();
}
