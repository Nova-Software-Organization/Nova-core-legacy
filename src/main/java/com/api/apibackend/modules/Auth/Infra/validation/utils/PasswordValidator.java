package com.api.apibackend.modules.Auth.Infra.validation.utils;

public class PasswordValidator {
    public String isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "A senha não pode estar em branco.";
        }

        if (password.length() < 8) {
            return "A senha deve ter pelo menos 8 caracteres.";
        }

        if (!password.matches(".*[A-Z].*")) {
            return "A senha deve conter pelo menos uma letra maiúscula.";
        }

        if (!password.matches(".*\\d.*")) {
            return "A senha deve conter pelo menos um número.";
        }

        return null;
    }
}
