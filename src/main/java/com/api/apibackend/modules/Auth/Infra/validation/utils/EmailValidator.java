package com.api.apibackend.modules.Auth.Infra.validation.utils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class EmailValidator {

    public List<String> isValidEmail(String email) {
        List<String> errorsList = new ArrayList<>();

        if (email == null || email.isEmpty()) {
            errorsList.add("O email não pode estar em branco.");
        }

        if (email.length() > 255) {
            errorsList.add("O email não pode ter mais de 255 caracteres.");
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(regex)) {
            errorsList.add("O email não é válido.");
        }

        if (email.contains("..")) {
            errorsList.add("O email não pode conter dois pontos consecutivos.");
        }

        if (email.startsWith(".") || email.endsWith(".")) {
            errorsList.add("O email não pode começar ou terminar com ponto.");
        }

        if (email.indexOf('@') == 0 || email.indexOf('@') == email.length() - 1) {
            errorsList.add("O caractere '@' não pode estar no início ou no final do email.");
        }

        int atIndex = email.indexOf('@');
        if (email.indexOf('@', atIndex + 1) != -1) {
            errorsList.add("O email não pode conter mais de um caractere '@'.");
        }

        if (email.contains("..")) {
            errorsList.add("O email não pode conter dois pontos consecutivos.");
        }

        if (!Character.isLetterOrDigit(email.charAt(0))) {
            errorsList.add("O primeiro caractere do email deve ser uma letra ou um dígito.");
        }

        if (email.startsWith("_") || email.endsWith("_")) {
            errorsList.add("O email não pode começar ou terminar com underscore (_)");
        }

        if (email.endsWith(".com") || email.endsWith(".com.")) {
            errorsList.add("O email não pode terminar com '.com' ou '.com.'");
        }

        if (email.contains("@exemplo@") || email.contains("@exemplo#")) {
            errorsList.add("O email não pode conter '@exemplo@' ou '@exemplo#'");
        }

        return errorsList.isEmpty() ? null : errorsList;
    }

    public boolean isEmailDomainValid(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String domain = parts[1];

        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            InitialDirContext ctx = new InitialDirContext(env);

            Attributes attrs = ctx.getAttributes(domain, new String[] { "MX" });

            return attrs != null && attrs.size() > 0;
        } catch (NamingException e) {
            return false;
        }
    }

    public boolean isValidEmailBoolean(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
