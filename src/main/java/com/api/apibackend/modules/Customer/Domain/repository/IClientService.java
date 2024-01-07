/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Domain.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;

@Repository
public interface IClientService {
    
    CustomerEntity createClient(CustomerEntity customerEntity, AddressEntity addressEntity);

    ResponseEntity<ResponseMessageDTO> update(Long clientId, CustomerDTO updatedClient, CustomerAddressDTO updatedAddress);

    ResponseEntity<ResponseMessageDTO> delete(Long clientId);
}
