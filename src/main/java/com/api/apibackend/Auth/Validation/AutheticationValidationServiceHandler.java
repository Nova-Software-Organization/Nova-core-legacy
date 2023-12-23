package com.api.apibackend.Auth.Validation;

import java.util.Hashtable;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

@Service
public class AutheticationValidationServiceHandler {
    private Validator validator;

    @Autowired
    public AutheticationValidationServiceHandler(Validator validator) {
        this.validator = validator;
    }

    public void validateClient(CustomerEntity customerEntity) {
        Set<ConstraintViolation<CustomerEntity>> violations = validator.validate(customerEntity);
        if (!violations.isEmpty()) {
            throw new ValidationException("Erros de validação encontrados");
        }
    }

    public void validateAddressClient(AddressEntity addressEntity) {
        Set<ConstraintViolation<AddressEntity>> violations = validator.validate(addressEntity);
        if (!violations.isEmpty()) {
            throw new ValidationException("Erros de validação encontrados");
        }
    }

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
		
		if (!password.matches(".*[a-z].*")) {
			return "A senha deve conter pelo menos uma letra minúscula.";
		}
		
		if (!password.matches(".*\\d.*")) {
			return "A senha deve conter pelo menos um número.";
		}

		return null;
	}

    public boolean isValidCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");
	
		if (cpf.length() != 11) {
			return false;
		}
	
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			int digit = Character.getNumericValue(cpf.charAt(i));
			sum += digit * (10 - i);
		}
		int firstDigit = 11 - (sum % 11);
	
		if (firstDigit == 10 || firstDigit == 11) {
			firstDigit = 0;
		}
	
		if (Character.getNumericValue(cpf.charAt(9)) != firstDigit) {
			return false;
		}

		sum = 0;
		for (int i = 0; i < 10; i++) {
			int digit = Character.getNumericValue(cpf.charAt(i));
			sum += digit * (11 - i);
		}

		int secondDigit = 11 - (sum % 11);
	
		if (secondDigit == 10 || secondDigit == 11) {
			secondDigit = 0;
		}
	
		if (Character.getNumericValue(cpf.charAt(10)) != secondDigit) {
			return false;
		}
	
		return true;
	}

    public String isValidEmail(String email) {
		if (email == null || email.isEmpty()) {
			return "O email não pode estar em branco.";
		}
		
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		if (!email.matches(regex)) {
			return "O email não é válido.";
		}

		return null;
	}

	public boolean isValidEmailBoolean(String email) {
		return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

	public String isValidCustomerAndCustomerAndCustomerAddress(CustomerDTO customerDTO, CustomerAddressDTO customerAddressDTO) {
		if (!isValidEmailBoolean(customerDTO.getEmail())) {
			return "E-mail inválido.";
		}
	
		if (!isValidCPF(customerDTO.getCpf())) {
			return "CPF inválido.";
		}
	
		if (customerDTO.getName() == null || customerDTO.getName().isEmpty()) {
			return "Nome do cliente não pode estar vazio.";
		}

		return null;
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
}
