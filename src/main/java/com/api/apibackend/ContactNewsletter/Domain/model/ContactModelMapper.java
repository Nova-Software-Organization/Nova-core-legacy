package com.api.apibackend.ContactNewsletter.Domain.model;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactRequest;
import com.api.apibackend.ContactNewsletter.infra.persistence.entity.ContactEntity;

@Service
public class ContactModelMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public ContactEntity toContactDTOAsContactEntity(ContactRequest request) {
        return this.modelMapper.map(request, ContactEntity.class);
    }

    public ContactRequest toContactDTOResponse(ContactEntity contactEntity) {
        return this.modelMapper.map(contactEntity, ContactRequest.class);
    }
}
