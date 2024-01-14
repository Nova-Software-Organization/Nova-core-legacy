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
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.token.TokenResetPasswordDTO;
import com.api.apibackend.modules.Auth.Domain.service.redefinePassword.RedefinePasswordService;

@Service
public class RedefinePasswordUserUseCase {
    private RedefinePasswordService redefinePasswordService;

    @Autowired
    public RedefinePasswordUserUseCase(RedefinePasswordService redefinePasswordService) {
        this.redefinePasswordService = redefinePasswordService;
    }

    public ResponseEntity<ResponseMessageDTO> execute(TokenResetPasswordDTO tokenResetPasswordDTO) {
        try {
            return redefinePasswordService.execute(tokenResetPasswordDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Erro", this.getClass().getSimpleName(),
                            "Ocorreu um erro ao processar a requisição!", null));
        }
    }
}
