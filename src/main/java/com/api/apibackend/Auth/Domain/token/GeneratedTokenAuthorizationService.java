package com.api.apibackend.Auth.Domain.token;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    public String generateToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = this.dateExpiration();
    
            return JWT.create()
                    .withIssuer("API api.backend")
                    .withSubject(userDetails.getUsername())
                    .withClaim("roles", Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
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
