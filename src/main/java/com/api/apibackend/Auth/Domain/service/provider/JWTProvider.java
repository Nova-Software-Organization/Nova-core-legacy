package com.api.apibackend.Auth.Domain.service.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.Data;

@Data
@Service
public class JWTProvider {

    @Value("${api.security.token.secret}")
    private String secret;

    public String validateToken(String token) {
        token = token.replace("Bearer", "");
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            String subject = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

            return subject;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return "";
        }
    }
}
