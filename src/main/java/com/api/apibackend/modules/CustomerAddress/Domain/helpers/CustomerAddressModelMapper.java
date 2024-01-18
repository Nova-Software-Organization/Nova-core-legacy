/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.CustomerAddress.Domain.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.CustomerAddressEntity;

@Service
public class CustomerAddressModelMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerAddressEntity toCustomerDTOFromAddressEntity(CustomerAddressDTO requestCustomerDTO) {
        return modelMapper.map(requestCustomerDTO, CustomerAddressEntity.class);
    }

    public CustomerAddressDTO toCustomerEntityFromCustomerAddressDTO(CustomerEntity requestCustomerEntity) {
        return modelMapper.map(requestCustomerEntity, CustomerAddressDTO.class);
    }
}
