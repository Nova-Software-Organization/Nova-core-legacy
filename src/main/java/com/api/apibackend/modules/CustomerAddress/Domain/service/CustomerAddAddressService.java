/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.CustomerAddress.Domain.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddAddressService {
    
	@Autowired
    private CustomerRepository clientRepository;

	public CustomerEntity addAddressToClient(Long clientId, AddressEntity addressEntity) {
		CustomerEntity client = clientRepository.findById(clientId).orElse(null);

		if (client != null) {
			client.setAddress(addressEntity);
			CustomerEntity updatedClient = clientRepository.save(client);

			return updatedClient;
		} else {
			return null;
		}
	}
}
