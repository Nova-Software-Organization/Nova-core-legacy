/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.CustomerAddress.Domain.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.CustomerAddress.infra.entity.AddressEntity;

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
