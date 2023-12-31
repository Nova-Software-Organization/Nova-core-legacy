package com.api.apibackend.ContactNewsletter.Application.controller;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactRequest;
import com.api.apibackend.ContactNewsletter.Application.repository.IContact;
import com.api.apibackend.ContactNewsletter.Application.useCase.ContactUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/contato")
public class ContactController implements IContact {
    @Autowired
    public ContactUseCase contactUseCase;

    @PostMapping("/newsletter")
    @Tag(name = "newsletter", description = "salva o contato do cliente, para envios de emails newsletter futuros!")
    public ResponseEntity<?> toReceiveContact(@RequestBody ContactRequest contactRequest) {
        return contactUseCase.executeContact(contactRequest);
    }
}
