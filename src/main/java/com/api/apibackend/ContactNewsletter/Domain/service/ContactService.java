/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.ContactNewsletter.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactDTO;
import com.api.apibackend.ContactNewsletter.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.ContactNewsletter.Domain.helper.ContactModelMapper;
import com.api.apibackend.ContactNewsletter.Domain.validation.ValidateContactClient;
import com.api.apibackend.ContactNewsletter.infra.persistence.entity.ContactEntity;
import com.api.apibackend.ContactNewsletter.infra.persistence.repository.ContactRepository;

@Service
public class ContactService {
    public ContactRepository contactRepository;
    private ValidateContactClient validateContactClientHandler;

    @Autowired
    public ContactService(ContactRepository contactRepository, ValidateContactClient validateContactClientHandler) {
        this.contactRepository = contactRepository;
        this.validateContactClientHandler = validateContactClientHandler;
    }

    public ResponseEntity<ResponseMessageDTO> createContact(ContactDTO contactRequest) {
        try {
            if (!validateContactClientHandler.validateContactHandler(contactRequest)) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("Dados de contato inválidos", this.getClass().getName(), null));
            }

            ContactModelMapper contactModelMapper = new ContactModelMapper();
            ContactEntity contactEntity = contactModelMapper.toContactDTOAsContactEntity(contactRequest);
            ContactEntity savedContact = contactRepository.save(contactEntity);

            if (savedContact != null) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseMessageDTO("Dados salvos com sucesso", this.getClass().getName(), null));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseMessageDTO("Erro ao salvar dados de contato", this.getClass().getName(),
                                null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
                            e.getMessage()));
        }
    }
}
