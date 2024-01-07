package com.api.apibackend.modules.Auth.Infra.validation;

import com.api.apibackend.modules.Auth.Infra.validation.utils.DocumentValidator;
import com.api.apibackend.modules.Auth.Infra.validation.utils.EmailValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AutheticationValidationServiceTest {

    @Mock
    private AutheticationValidationServiceHandler autheticationValidationServiceHandler;

    @InjectMocks
    private AutheticationValidationServiceTest autheticationValidationService;

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

        EmailValidator emailValidator = new EmailValidator();
        Mockito.lenient().when(autheticationValidationServiceHandler.isValidEmail(anyString())).thenAnswer(invocation -> {
            String email = invocation.getArgument(0);
            return emailValidator.isValidEmail(email);
        });

        for (String invalidEmail : customerInvalidEmails) {
            List<String> validationErrors = autheticationValidationServiceHandler.isValidEmail(invalidEmail);
            if (validationErrors != null && !validationErrors.isEmpty()) {
                errorsList.addAll(validationErrors);
                System.out.println("Validation errors for invalid email: " + invalidEmail + " - " + validationErrors);
            }
        }

        assertEquals(5, errorsList.size(), "Pelo menos 5 e-mails devem ser inválidos.");
        if (!errorsList.isEmpty()) {
            errorsList.forEach((erros) ->
                    System.out.println("Emails que causaram erro de validação: " + String.join(", ", erros + "\n"))
            );}
    }

    @Test
    @DisplayName("TestRegisterInvalidDocument")
    public void testRegister_When_InvalidDocument_ReturnError() {
        Set<String> errorsList = new HashSet<>();
        List<String> customerInvalidCpfs = Arrays.asList(
                "123.456.789-00",
                "987.654.321-01",
                "000.111.222-33",
                "999.888.777-66",
                "12345678900"
        );

        DocumentValidator documentValidator = new DocumentValidator();
        Mockito.lenient().when(autheticationValidationServiceHandler.isValidCPF(anyString())).thenAnswer(invocation -> {
            String cpf = invocation.getArgument(0);
            return documentValidator.isValidCPF(cpf);
        });

        for (String invalidCpf : customerInvalidCpfs) {
            boolean isValid = documentValidator.isValidCPF(invalidCpf);
            if (!isValid) {
                errorsList.add("CPF inválido: " + invalidCpf);
                System.out.println("CPF se encontra inválido: " + invalidCpf);
            }
        }

        assertTrue(errorsList.size() >= 5, "Pelo menos 5 CPFs devem ser inválidos.");
        if (!errorsList.isEmpty()) {
            System.out.println("CPFs que causaram erro de validação: \n" + String.join(", ", customerInvalidCpfs));
        }
    }
}
