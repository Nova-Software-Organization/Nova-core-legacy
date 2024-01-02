/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Midia.Domain.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Modules.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.Modules.Midia.infra.persistence.entity.MidiaEntity;

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
