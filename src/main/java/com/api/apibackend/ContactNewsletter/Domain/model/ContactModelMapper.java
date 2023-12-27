package com.api.apibackend.ContactNewsletter.Domain.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.ContactNewsletter.Application.DTOs.ContactRequest;
import com.api.apibackend.ContactNewsletter.infra.Persistence.entity.ContactEntity;

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
