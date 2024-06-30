/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases.ResetPasswordCustomer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Auth.Application.DTOs.mail.AuthUserResetPassawordDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/auth")
public class ResetPasswordGeneratedTokenController {
    private final ResetPasswordGeneratedTokenUseCase resetPasswordGeneratedTokenUseCase;

    @Autowired
    public ResetPasswordGeneratedTokenController(ResetPasswordGeneratedTokenUseCase resetPasswordGeneratedTokenUseCase) {
        this.resetPasswordGeneratedTokenUseCase = resetPasswordGeneratedTokenUseCase;
    }

    @PostMapping(path = "/redefinir/senha")
    @Tag(name = "Refine a senha", description = "Envia um email para o usuário com um token aleatório para a confirmação de redefinição de senha")
    @Operation(summary = "Rota responsável por redefinir a senha do usuário")
    public ResponseEntity<ResponseMessageDTO> handle(@RequestBody AuthUserResetPassawordDTO authUserResetPassawordDTO) {
        try {
            String email = Optional.ofNullable(authUserResetPassawordDTO.getEmail()).orElse("");
            String username = Optional.ofNullable(authUserResetPassawordDTO.getUsername()).orElse("");
            if (username.isEmpty() && email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseMessageDTO("Erro", this.getClass().getName(),
                                "É necessário fornecer pelo menos um email ou um nome de usuário", null));
            }

            return resetPasswordGeneratedTokenUseCase.execute(authUserResetPassawordDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageDTO("Erro", this.getClass().getName(), e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Erro", this.getClass().getName(),
                            "Ocorreu um erro inesperado: " + e.getMessage(), null));
        }
    }
}
