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
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.mail.AuthUserResetPassawordDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Domain.service.resetPassword.ResetPasswordAuthUserService;

@Service
public class ResetPasswordGeneratedTokenUseCase {
    private ResetPasswordAuthUserService resetPasswordAuthUserService;

    @Autowired
    public ResetPasswordGeneratedTokenUseCase(ResetPasswordAuthUserService resetPasswordAuthUserService) {
        this.resetPasswordAuthUserService = resetPasswordAuthUserService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(AuthUserResetPassawordDTO authUserResetPassawordDTO) {
        try {
            Optional.ofNullable(authUserResetPassawordDTO).orElseThrow();
            resetPasswordAuthUserService.sendResetPasswordEmail(authUserResetPassawordDTO);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
            "Solicitação de redefinição de senha processada com sucesso", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Erro ao processar a solicitação de registro: " + e.getMessage(), null));
        }
    }
}
