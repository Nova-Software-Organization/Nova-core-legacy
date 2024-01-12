/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.ContactNewsletter.Domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ContactDTO;
import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.ContactNewsletter.Domain.validation.ValidateContactClient;
import com.api.apibackend.modules.ContactNewsletter.infra.persistence.entity.ContactEntity;
import com.api.apibackend.modules.ContactNewsletter.infra.persistence.repository.ContactRepository;
import com.api.apibackend.shared.helpers.ModelMappersConvertion;

@Service
public class ContactService {
    public ContactRepository contactRepository;
    private ValidateContactClient validateContactClientHandler;

    @Autowired
    public ContactService(ContactRepository contactRepository, ValidateContactClient validateContactClientHandler) {
        this.contactRepository = contactRepository;
        this.validateContactClientHandler = validateContactClientHandler;
    }

    public ResponseEntity<ResponseMessageDTO> createContact(ContactDTO contactDTO) {
        try {
            if (!validateContactClientHandler.validateContactHandler(contactDTO)) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("Dados de contato inválidos", this.getClass().getName(), null));
            }

            ModelMappersConvertion<ContactDTO, ContactEntity> contactModelMapper = new ModelMappersConvertion<>(
                    new ModelMapper());
            ContactEntity contactEntity = contactModelMapper.toDTOFromEntity(contactDTO, ContactEntity.class);
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
