/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.CustomerAddress.Domain.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.Modules.CustomerAddress.infra.entity.AddressEntity;

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
