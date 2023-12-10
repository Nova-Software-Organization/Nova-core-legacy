package com.api.apibackend.Auth.Domain.token;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
@Component
public class GeneratedTokenAuthorizationService {
    
    @Value("${api.security.token.secret}")
    private final String secret;

    @Autowired
    public GeneratedTokenAuthorizationService(@Value("${api.security.token.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(String username, Set<CustomGrantedAuthority> customGrantedAuthorities) {
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
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }
    
    public String getSubject(String tokenJWT) {
        try {
            var algoritm = Algorithm.HMAC256(secret);
            return JWT.require(algoritm)
                    .withIssuer("API api.backend")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Date dateExpiration() {
        return new Date(System.currentTimeMillis() + 3600000);
    }
}
