/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.provider.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Domain.service.user.MyUserDetailsService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
@Service
public class JWTProvider {
    private String secretKey = "12345678";
    private MyUserDetailsService myUserDetails;

    public String validateToken(String token) {
        token = token.replace("Bearer", "").trim();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
    
        try {
            String subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
    
            return subject;
        } catch (JWTVerificationException exception) {
            System.out.println("Erro ao validar o token: " + exception.getMessage());
            exception.printStackTrace();
            return "";
        }
    }
    

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
