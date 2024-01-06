package com.api.apibackend.Modules.Auth.Infra.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class AutheticationValidationService {
    @Mock
    private AutheticationValidationServiceHandler autheticationValidationServiceHandler;

    @Test
    @DisplayName("TestRegisterInvalidEmail")
    public void testRegister_When_InvalidEmail_ReturnError() {
        Set<String> errorsList = new HashSet<>();
        List<String> customerInvalidEmails = Arrays.asList(
                "test@gmail.com",
                "teste12322142@gmail.com",
                "helloworld@gmail.com",
                "emailsem@arrouba.çom",
                "email@exemplo..com",
                "email@exemplo",
                "email@exemplo.c",
                "email@.com",
                "email@com.",
                "email@exemplo,com",
                "email@exemplo@com",
                "email@exemplo#com",
                "email@exemplo@com."
        );

        for (String invalidEmail : customerInvalidEmails) {
            Optional<String> validationError = Optional.ofNullable(autheticationValidationServiceHandler.isValidEmail(invalidEmail));
            validationError.ifPresent(error -> {
                errorsList.add(error);
                System.out.println("Erro de validação para o email inválido: " + invalidEmail + " - " + error);
            });
        }

        assertTrue(errorsList.isEmpty(), "Não deveria haver erros de validação para os e-mails fornecidos.");
        assertEquals(customerInvalidEmails.size(), errorsList.size(),
                "O número de erros deve ser igual ao número de emails inválidos.");
    }
}
