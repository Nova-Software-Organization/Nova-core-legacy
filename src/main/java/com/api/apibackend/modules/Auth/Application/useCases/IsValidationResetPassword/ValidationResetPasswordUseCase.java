/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases.IsValidationResetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.token.TokenResetPasswordDTO;
import com.api.apibackend.modules.Auth.Domain.service.resetPasswordValidate.ResetPasswordValidateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidationResetPasswordUseCase {
    private ResetPasswordValidateService resetPasswordValidateService;

    @Autowired
    public ValidationResetPasswordUseCase(ResetPasswordValidateService resetPasswordValidateService) {
        this.resetPasswordValidateService = resetPasswordValidateService;
    }
    
    public ResponseEntity<ResponseMessageDTO> execute(TokenResetPasswordDTO tokenResetPasswordDTO) {
        try {
            boolean isValidToken = resetPasswordValidateService.validateToken(tokenResetPasswordDTO);

            if (isValidToken) {
                log.info("token infomado valido : {}");
                return ResponseEntity.ok(new ResponseMessageDTO("token válido", this.getClass().getName(), null, null));
            }
            
            log.info("Token informado inválido : {}");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Token informado inválido!", null));
        } catch (Exception ex) {
            log.error("Ocorreu um erro ao processar a requisição, token : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO(null, this.getClass().getSimpleName(),
                            "Ocorreu um erro ao processar a requisição!" + ex.getMessage(), null));
        }
    }
}
