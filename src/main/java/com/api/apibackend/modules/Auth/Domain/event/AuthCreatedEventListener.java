package com.api.apibackend.modules.Auth.Domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.api.apibackend.modules.Auth.Domain.service.cryptography.AnonymizationService;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Mail.Domain.service.auth.resetPassword.EmailSenderService;
import com.api.apibackend.shared.Event.Domain.IDomainEventListener;

@Component
public class AuthCreatedEventListener implements IDomainEventListener<AuthCreated> {
    private final UserRepository userRepository;
    private final AnonymizationService anonymizationService;
    private final EmailSenderService emailSenderService;

    @Autowired
    public AuthCreatedEventListener(UserRepository userRepository, AnonymizationService anonymizationService, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.anonymizationService = anonymizationService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    @EventListener
    public void onEvent(AuthCreated event) {
        Long createdUser = event.getCreatedUser();
        userRepository.findById(createdUser)
                .ifPresent(userEntity -> {
                    String userEmailCryptography = userEntity.getEmail();
                    String email = anonymizationService.decrypt(userEmailCryptography);
                    emailSenderService.sendEmail(email, null, "account-creation-notification", userEntity.getUsername());
                });
    }
}
