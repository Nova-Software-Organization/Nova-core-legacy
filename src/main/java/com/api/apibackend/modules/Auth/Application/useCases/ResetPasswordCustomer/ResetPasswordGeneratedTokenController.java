/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases.ResetPasswordCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Auth.Application.DTOs.AuthAccessToken;
import com.api.apibackend.modules.Auth.Application.DTOs.mail.AuthUserResetPassawordDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/auth")
public class ResetPasswordGeneratedTokenController {
    private ResetPasswordGeneratedTokenUseCase resetPasswordGeneratedTokenUseCase;
    
    @Autowired
    public ResetPasswordGeneratedTokenController(ResetPasswordGeneratedTokenUseCase resetPasswordGeneratedTokenUseCase) {
        this.resetPasswordGeneratedTokenUseCase = resetPasswordGeneratedTokenUseCase;
    }
    
    @PostMapping(path = "/redefinir/senha")
    @Tag(name = "Refine a senha", description = "Envia um email para usuario com token a aléatoria, para a confirmação de redefinição de senha do usuario")
    @Operation(summary = "Rota responsavel por redefinir a senha do usuario")
    public ResponseEntity<ResponseMessageDTO> handle(@RequestBody AuthUserResetPassawordDTO authUserResetPassawordDTO) {
        if (!authUserResetPassawordDTO.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessageDTO("Email enviado com sucesso!", this.getClass().getName(), null, null));
        }

        return resetPasswordGeneratedTokenUseCase.execute(authUserResetPassawordDTO);
    }
}
