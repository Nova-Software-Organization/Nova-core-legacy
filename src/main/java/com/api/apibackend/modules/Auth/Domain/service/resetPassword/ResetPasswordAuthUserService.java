package com.api.apibackend.modules.Auth.Domain.service.resetPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Application.DTOs.mail.AuthUserResetPassawordDTO;
import com.api.apibackend.modules.Auth.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class ResetPasswordAuthUserService {
    private UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public ResetPasswordAuthUserService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public ResponseEntity<ResponseMessageDTO> sendResetPasswordEmail(AuthUserResetPassawordDTO authUserResetPassawordDTO) {
        return Optional.ofNullable(userRepository.findByEmail(authUserResetPassawordDTO.getEmail()))
                .map(user -> {
                    String resetCode = generateRandomCode();
                    sendEmail(user.getEmail(), resetCode);
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Sucesso", this.getClass().getName(), "Email enviado com sucesso", null));
                })
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Erro", this.getClass().getName(), "Email fornecido não encontrado", null)));
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void sendEmail(String to, String resetCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Redefinição de Senha - Código de Verificação");
        message.setText("Seu código de verificação para redefinição de senha é: " + resetCode);

        mailSender.send(message);
    }
}
