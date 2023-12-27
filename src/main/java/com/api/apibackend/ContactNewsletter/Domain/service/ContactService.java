package com.api.apibackend.ContactNewsletter.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactRequest;
import com.api.apibackend.ContactNewsletter.Domain.model.ContactModelMapper;
import com.api.apibackend.ContactNewsletter.Domain.validation.ValidateContactClient;
import com.api.apibackend.ContactNewsletter.infra.Persistence.entity.ContactEntity;
import com.api.apibackend.ContactNewsletter.infra.Persistence.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	public ContactRepository contactRepository;

	private ValidateContactClient validateContactClientHandler;

	public ResponseEntity<?> createContact(ContactRequest contactRequest) {
		if (contactRequest != null && validateContactClientHandler.validateContactHandler(contactRequest)) {
			ContactModelMapper emailModelMapper = new ContactModelMapper();
			boolean validatedContact = validateContactClientHandler.validateContactHandler(contactRequest);
			
			if (validatedContact) {
				ContactEntity savedContact = emailModelMapper.toContactDTOAsContactEntity(contactRequest);
				contactRepository.save(savedContact);
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Dados salvos com sucesso");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: dados não existem");
	}
}
	
