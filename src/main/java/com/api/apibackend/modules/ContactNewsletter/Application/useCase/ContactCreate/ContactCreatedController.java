/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ContactNewsletter.Application.useCase.ContactCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ContactDTO;
import com.api.apibackend.modules.ContactNewsletter.Application.repository.IContact;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/contato")
public class ContactCreatedController implements IContact {
    @Autowired
    public ContactCreatedUseCase contactUseCase;

    @PostMapping("/criar")
    @Tag(name = "newsletter", description = "salva o contato do cliente, para envios de emails newsletter futuros!")
    public ResponseEntity<?> toReceiveContact(@RequestBody ContactDTO contactRequest) {
        return contactUseCase.executeContact(contactRequest);
    }
}
