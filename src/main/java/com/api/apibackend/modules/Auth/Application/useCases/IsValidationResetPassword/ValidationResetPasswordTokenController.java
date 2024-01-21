/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.useCases.IsValidationResetPassword;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.token.TokenResetPasswordDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/auth")
public class ValidationResetPasswordTokenController {
    private ValidationResetPasswordUseCase validationResetPasswordUseCase;

    @PostMapping(path = "/validar/token/redefinicao/senha")
    @Tag(name = "Verifica o token informado, para redefinir a senha", description = "Faz as verificações para redefinição de senha do usuário")
    @Operation(summary = "Rota responsável por validar  o token informado pelo usuario")
    public ResponseEntity<ResponseMessageDTO> handle(@RequestBody TokenResetPasswordDTO tokenResetPasswordDTO) {
        try {
            validationResetPasswordUseCase.execute(tokenResetPasswordDTO);
            log.info("Token válido : {} step 1");
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessageDTO("Sucesso", this.getClass().getSimpleName(),
                        "Token válido", null));
        } catch (Exception ex) {
            log.error("Ocorreu um erro ao processar o token", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getSimpleName(),
                            ex.getMessage(), null));
        }
    }
}
