package com.api.apibackend.modules.Auth.Application.useCases.ResetPasswordCustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.mail.AuthUserResetPassawordDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Mail.Domain.service.resetPassword.ResetPasswordAuthUserService;

@Service
public class ResetPasswordGeneratedTokenUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordGeneratedTokenUseCase.class);

    private ResetPasswordAuthUserService resetPasswordAuthUserService;

    @Autowired
    public ResetPasswordGeneratedTokenUseCase(ResetPasswordAuthUserService resetPasswordAuthUserService) {
        this.resetPasswordAuthUserService = resetPasswordAuthUserService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(AuthUserResetPassawordDTO authUserResetPassawordDTO) {
        try {
            resetPasswordAuthUserService.sendResetPasswordEmail(authUserResetPassawordDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessageDTO("Sucesso! email enviado :)", this.getClass().getSimpleName(),
                            "Solicitação de redefinição de senha processada com sucesso", null));
        } catch (Exception e) {
            logger.error("Erro ao processar a solicitação de redefinição de senha", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Erro ao processar a solicitação de redefinição de senha. Detalhes: " + e.getMessage(), null));
        }
    }
}
