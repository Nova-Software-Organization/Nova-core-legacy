package com.api.apibackend.CustomerAddress.Domain.helpers;

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

import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

@Service
public class CustomerAddressModelMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public AddressEntity toCustomerDTOFromAddressEntity(CustomerAddressDTO requestCustomerDTO) {
        return modelMapper.map(requestCustomerDTO, AddressEntity.class);
    }

    public CustomerAddressDTO toCustomerEntityFromCustomerAddressDTO(CustomerEntity requestCustomerEntity) {
        return modelMapper.map(requestCustomerEntity, CustomerAddressDTO.class);
    }
}
