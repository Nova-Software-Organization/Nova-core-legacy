/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Modules.Customer.Domain.repository.IClientService;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.Modules.CustomerAddress.infra.entity.AddressEntity;
import com.api.apibackend.Modules.CustomerAddress.infra.repository.AddressRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService implements IClientService {
	private CustomerRepository customerRepository;
	private AddressRepository addressRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}

	public CustomerEntity createClient(CustomerEntity customerEntity, AddressEntity addressEntity) {
		customerEntity.setAddress(addressEntity);
		CustomerEntity savedClient = customerRepository.save(customerEntity);
		return savedClient;
	}

	@Transactional
	public ResponseEntity<ResponseMessageDTO> update(Long clientId, CustomerDTO updatedClient,
			CustomerAddressDTO updatedAddress) {
		Optional<CustomerEntity> existingClient = customerRepository.findById(clientId);
		Optional<AddressEntity> existingAddress = addressRepository.findById(clientId);

		if (existingClient.isPresent()) {
			CustomerEntity clientToUpdate = existingClient.get();
			clientToUpdate.setPhone(updatedClient.getPhone());
		}

		if (existingAddress.isPresent()) {
			AddressEntity addressToUpdate = existingAddress.get();
			addressToUpdate.setCep(updatedAddress.getCep() != addressToUpdate.getCep() ? updatedAddress.getCep()
					: addressToUpdate.getCep());
			addressToUpdate.setRoad(updatedAddress.getRoad() != addressToUpdate.getRoad() ? updatedAddress.getRoad()
					: addressToUpdate.getRoad());
			addressToUpdate.setNeighborhood(updatedAddress.getNeighborhood() != addressToUpdate.getNeighborhood()
					? updatedAddress.getNeighborhood()
					: addressToUpdate.getNeighborhood());
			addressToUpdate.setHousenumber(updatedAddress.getHousenumber() != addressToUpdate.getHousenumber()
					? updatedAddress.getHousenumber()
					: addressToUpdate.getHousenumber());
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessageDTO("Dados atualizados com sucesso", this.getClass().getName(), null));
	}

	@Transactional
	public ResponseEntity<ResponseMessageDTO> delete(Long clientId) {
		Optional<CustomerEntity> existingClient = customerRepository.findById(clientId);

		if (existingClient.isPresent()) {
			customerRepository.delete(existingClient.get());
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessageDTO("Deletado com sucesso", this.getClass().getName(), null));
	}
}
