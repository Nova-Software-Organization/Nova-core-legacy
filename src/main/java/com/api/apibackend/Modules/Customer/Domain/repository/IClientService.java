/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Domain.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Modules.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.CustomerAddress.infra.entity.AddressEntity;

@Repository
public interface IClientService {
    
    CustomerEntity createClient(CustomerEntity customerEntity, AddressEntity addressEntity);

    ResponseEntity<ResponseMessageDTO> update(Long clientId, CustomerDTO updatedClient, CustomerAddressDTO updatedAddress);

    ResponseEntity<ResponseMessageDTO> delete(Long clientId);
}
