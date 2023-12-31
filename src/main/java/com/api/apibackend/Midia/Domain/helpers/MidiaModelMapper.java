package com.api.apibackend.Midia.Domain.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.Midia.infra.persistence.entity.MidiaEntity;

public class MidiaModelMapper {
    private ModelMapper modelMapper;

    @Autowired
    public MidiaModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MidiaEntity toMidiaDTOFromMidiaEntity(MidiaDTO midiaDTO) {
        return modelMapper.map(midiaDTO, MidiaEntity.class);
    }

    public MidiaDTO toMidiaEntityFromMidiaRequest(MidiaEntity requestMidiaEntity) {
        return modelMapper.map(requestMidiaEntity, MidiaDTO.class);
    }
}
