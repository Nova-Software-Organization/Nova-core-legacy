package com.api.apibackend.modules.Auth.Domain.service.resetPassword;

import com.api.apibackend.modules.Auth.Domain.provider.GenerateRandomCodeResetPasswordProvider;
import com.api.apibackend.modules.Auth.Domain.service.AnonymizationService;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Mail.Domain.service.EmailSenderServiceMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.mail.AuthUserResetPassawordDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;

import java.util.Optional;

@Service
public class ResetPasswordAuthUserService {
    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordAuthUserService.class);

    private final UserRepository userRepository;
    private final EmailSenderServiceMail mailSendResetPassword;
    private final AnonymizationService anonymizationService;

    @Autowired
    public ResetPasswordAuthUserService(UserRepository userRepository, EmailSenderServiceMail mailSendResetPassword, AnonymizationService anonymizationService) {
        this.userRepository = userRepository;
        this.mailSendResetPassword = mailSendResetPassword;
        this.anonymizationService = anonymizationService;
    }

    public ResponseEntity<ResponseMessageDTO> sendResetPasswordEmail(AuthUserResetPassawordDTO authUserResetPassawordDTO) {
        try {
            UserEntity userByEmail = userRepository.findByEmail(authUserResetPassawordDTO.getEmail());
            if (userByEmail != null) {
                return applyAndRespond(userByEmail);
            }

            // Se o usuário por e-mail não foi encontrado, tenta buscar pelo nome de usuário
            return Optional.ofNullable(userRepository.findByUsername(authUserResetPassawordDTO.getUsername()))
                    .map(this::applyAndRespond)
                    .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseMessageDTO("Erro", this.getClass().getName(),
                                    "Nenhum usuário encontrado para o e-mail ou nome de usuário fornecido", null)));
        } catch (Exception e) {
            logger.error("Erro ao enviar email de redefinição de senha", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Erro", this.getClass().getName(),
                            "Erro ao processar a solicitação de redefinição de senha: " + e.getMessage(), null));
        }
    }

    private ResponseEntity<ResponseMessageDTO> applyAndRespond(UserEntity user) {
        try {
            return apply(user);
        } catch (Exception e) {
            logger.error("Erro ao enviar email de redefinição de senha", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Erro", this.getClass().getName(),
                            "Erro ao processar a solicitação de redefinição de senha: " + e.getMessage(), null));
        }
    }

    private ResponseEntity<ResponseMessageDTO> apply(UserEntity user) {
        GenerateRandomCodeResetPasswordProvider generateRandomCodeResetPasswordProvider = new GenerateRandomCodeResetPasswordProvider();
        String resetCode = generateRandomCodeResetPasswordProvider.generateRandomCode();
        String emailUser = anonymizationService.decrypt(user.getEmail());
        mailSendResetPassword.sendEmail(emailUser, resetCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Sucesso", this.getClass().getName(), "Email enviado com sucesso", null));
    }

}
