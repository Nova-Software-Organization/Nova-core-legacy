package com.api.apibackend.Customer.Infra.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtills {
    
    @Value("${security.jwt.secret}")
    private String secret;

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
}
