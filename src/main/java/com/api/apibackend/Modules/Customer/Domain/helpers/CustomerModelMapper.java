/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Domain.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class CustomerModelMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity toCustomerDTOFromCustomerEntity(CustomerDTO requestCustomerDTO) {
        return modelMapper.map(requestCustomerDTO, CustomerEntity.class);
    }

    public CustomerDTO toCustomerEntityFromCustomerDTO(CustomerEntity requestCustomerEntity) {
        return modelMapper.map(requestCustomerEntity, CustomerDTO.class);
    }
}
