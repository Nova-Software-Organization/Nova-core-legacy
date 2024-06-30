/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases.RedefinePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.token.TokenResetPasswordDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/auth")
public class RedefinePasswordUserController {
    private RedefinePasswordUserUseCase redefinePasswordUserUseCase;

    @Autowired
    public RedefinePasswordUserController(RedefinePasswordUserUseCase redefinePasswordUserUseCase) {
        this.redefinePasswordUserUseCase = redefinePasswordUserUseCase;
    }

    @PostMapping("/redefinir-senha")
    @Tag(name = "Redefinir Senha", description = "Endpoint para redefinir a senha do usuário")
    @Operation(summary = "Redefinir a senha do usuário com base no token fornecido")
    public ResponseEntity<ResponseMessageDTO> handle(TokenResetPasswordDTO tokenResetPasswordDTO) {
        try {
            ResponseEntity<ResponseMessageDTO> response = redefinePasswordUserUseCase.execute(tokenResetPasswordDTO);
            return response;
        } catch (Exception e) {
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage(), null));
        }
    }
}
