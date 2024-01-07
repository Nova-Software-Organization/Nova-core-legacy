/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Auth.Infra.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;

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

	public boolean isValidEmailBoolean(String email) {
		return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

	public String isValidCustomerAndCustomerAndCustomerAddress(CustomerDTO customerDTO,
			CustomerAddressDTO customerAddressDTO) {
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
}
