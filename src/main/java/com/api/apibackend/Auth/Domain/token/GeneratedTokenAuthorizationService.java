package com.api.apibackend.Auth.Domain.token;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Auth.Domain.exception.GenerateTokenErrorException;
import com.api.apibackend.Auth.Domain.exception.InvalidGenerateTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
@Component
public class GeneratedTokenAuthorizationService {
    private String secret = "12345678";

    public String generateToken(String username, Set<CustomGrantedAuthority> customGrantedAuthorities) throws GenerateTokenErrorException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = this.dateExpiration();
            
            List<String> userRoles = customGrantedAuthorities.stream()
                    .map(CustomGrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            
            return JWT.create()
                    .withIssuer("API api.backend")
                    .withSubject(username)
                    .withClaim("roles", userRoles)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new GenerateTokenErrorException(e);
        }
    }
    
    public String getSubject(String tokenJWT) throws InvalidGenerateTokenException {
        try {
            var algoritm = Algorithm.HMAC256(secret);
            return JWT.require(algoritm)
                    .withIssuer("API api.backend")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidGenerateTokenException("Token JWT inválido ou expirado!");
        }
    }

    private Date dateExpiration() {
        return new Date(System.currentTimeMillis() + 3600000);
    }
}
