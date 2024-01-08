/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ContactNewsletter.Application.useCase.ContactCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ContactDTO;
import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.ContactNewsletter.Domain.service.ContactService;

@Service
public class ContactCreatedUseCase {

    @Autowired
    public ContactService contactService;

    public ResponseEntity<ResponseMessageDTO> executeContact(ContactDTO contactRequest) {
        try {
            if (contactRequest != null) {
                return contactService.createContact(contactRequest);
            } else {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessageDTO("Erro: Dados de contato ausentes", this.getClass().getName(),
                                null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Erro interno ao processar a solicitação", this.getClass().getName(),
                            e.getMessage()));
        }
    }
}
