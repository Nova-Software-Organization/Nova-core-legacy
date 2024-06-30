/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.ContactNewsletter.Domain.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.ContactNewsletter.Application.DTOs.ContactDTO;
import com.api.apibackend.modules.ContactNewsletter.infra.persistence.entity.ContactEntity;

@Service
public class ContactModelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ContactEntity toContactDTOAsContactEntity(ContactDTO request) {
        return this.modelMapper.map(request, ContactEntity.class);
    }

    public ContactDTO toContactDTOResponse(ContactEntity contactEntity) {
        return this.modelMapper.map(contactEntity, ContactDTO.class);
    }
}
