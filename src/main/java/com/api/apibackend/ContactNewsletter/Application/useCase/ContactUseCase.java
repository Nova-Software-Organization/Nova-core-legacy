package com.api.apibackend.ContactNewsletter.Application.useCase;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactRequest;
import com.api.apibackend.ContactNewsletter.Domain.service.ContactService;

@Service
public class ContactUseCase {
	
	@Autowired
	public ContactService contactService;
	
	public ResponseEntity<?> executeContact(ContactRequest contactRequest) {
		if (contactRequest != null) {
			return contactService.createContact(contactRequest);
		}
		
		return ResponseEntity.badRequest().body("Erro de validação");
	}
}
