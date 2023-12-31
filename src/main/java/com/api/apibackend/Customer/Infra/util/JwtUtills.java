package com.api.apibackend.Customer.Infra.util;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.api.apibackend.Auth.Domain.provider.JWTProvider;
import com.api.apibackend.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.Auth.Infra.persistence.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtills {
    private UserRepository userRepository;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;
    private JWTProvider jwtTokenProvider;
    
    @Value("${security.jwt.secret}")
    private String secret;

    @Autowired
    public JwtUtills(UserRepository userRepository, GeneratedTokenAuthorizationService generatedTokenAuthorizationService, JWTProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

      public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            Date expirationDate = claims.getExpiration();
            
            if (expirationDate != null && expirationDate.before(new Date())) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

  public UserEntity whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return generatedTokenAuthorizationService.generateToken(username, userRepository.findByUsername(username).getRoles());
  }
}
