/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Infra.validation;

import com.api.apibackend.modules.Auth.Infra.validation.repository.ValidationFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AuthenticationValidationHandlerServiceTest {

    @Mock
    private AuthenticationValidationServiceHandler validationServiceHandler;

    @InjectMocks
    private AuthenticationValidationHandlerServiceTest authenticationValidationHandlerServiceTest;

    private static final int MINIMUM_INVALID_ENTRY_COUNT = 5;

    @Test
    @DisplayName("TestRegisterInvalidEmail")
    public void testRegister_When_InvalidEmail_ReturnError() {
        List<String> invalidEmails = Arrays.asList(
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

        validateInvalidEntries("Emails", invalidEmails, MINIMUM_INVALID_ENTRY_COUNT, email -> Boolean.parseBoolean(String.valueOf(validationServiceHandler.isValidEmail(email))));
    }

    @Test
    @DisplayName("TestRegisterInvalidDocument")
    public void testRegister_When_InvalidDocument_ReturnError() {
        List<String> invalidCpfs = Arrays.asList(
                "123.456.789-00",
                "987.654.321-01",
                "000.111.222-33",
                "999.888.777-66",
                "12345678900"
        );

        validateInvalidEntries("CPFs", invalidCpfs, MINIMUM_INVALID_ENTRY_COUNT, validationServiceHandler::isValidCPF);
    }

    @Test
    @DisplayName("TestRegisterInvalidPassword")
    public void test_Register_When_InvalidPassword_ReturnError() {
        List<String> invalidPasswords = Arrays.asList(
                "kfjslfkdsB",
                "987.654.321-01",
                "000.111.222-33",
                "999.888.777-66",
                "12345678900",
                "WeakPassword123",
                "password",
                "123456789",
                "abcdefghi"
        );

        validateInvalidEntries("Passwords", invalidPasswords, invalidPasswords.size(), password -> Boolean.parseBoolean(validationServiceHandler.isValidPassword(password)));
    }

    private void validateInvalidEntries(String entryType, List<String> invalidEntries, int minimumInvalidCount, ValidationFunction validationFunction) {
        Set<String> errorsList = new HashSet<>();

        for (String invalidEntry : invalidEntries) {
            boolean isValid = validationFunction.validate(invalidEntry);
            if (!isValid) {
                errorsList.add(entryType + " inválido: " + invalidEntry);
                System.out.println(entryType + " se encontra inválido: " + invalidEntry);
            }
        }

        assertTrue(errorsList.size() >= minimumInvalidCount, "Pelo menos " + minimumInvalidCount + " " + entryType + " devem ser inválidos.");
        printErrors(entryType + " que causaram erro de validação", errorsList);
    }

    private void printErrors(String message, Set<String> errorsList) {
        if (!errorsList.isEmpty()) {
            errorsList.forEach((error) ->
                    System.out.println(message + ": " + String.join(", ", error + "\n"))
            );
        }
    }
}
