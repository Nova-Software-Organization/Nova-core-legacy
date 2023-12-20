package com.api.apibackend.Customer.Domain.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

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
