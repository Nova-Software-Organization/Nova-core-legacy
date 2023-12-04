package com.api.apibackend.CustomerAddress.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

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
