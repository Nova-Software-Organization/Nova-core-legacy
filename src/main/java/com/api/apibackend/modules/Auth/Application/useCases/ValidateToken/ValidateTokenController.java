/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Auth.Application.useCases.ValidateToken;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Auth.Application.DTOs.AuthAccessToken;
import com.api.apibackend.modules.Customer.Infra.util.JwtUtills;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/auth")
public class ValidateTokenController {
    private JwtUtills jwtUtills;

    @PostMapping(path = "/valida/token")
    @Tag(name = "Valida token", description = "Verifica se o token do usuário está expirado")
    @Operation(summary = "Rota responsavel por validar o token do usuário para ver se já está expirado ou não")
    public ResponseEntity<String> handle(@RequestBody AuthAccessToken token) {
        if (token.getAccess_token().isEmpty()) {
            String valueToken = token.getAccess_token();
            jwtUtills.validateToken(valueToken);
            return ResponseEntity.status(HttpStatus.OK).body("Token valido");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalido!");
    }
}
