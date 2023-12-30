package com.api.apibackend.Customer.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerAddressDTO;
import com.api.apibackend.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.Customer.Domain.repository.IClientService;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;
import com.api.apibackend.CustomerAddress.infra.repository.AddressRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImp implements IClientService {
	private CustomerRepository clientRepository;
	private AddressRepository addressRepository;
	
	@Autowired
	public CustomerServiceImp(CustomerRepository clientRepository, AddressRepository addressRepository) {
		this.clientRepository = clientRepository;
		this.addressRepository = addressRepository;
	}
	
	public CustomerEntity createClient(CustomerEntity customerEntity, AddressEntity addressEntity) {
		customerEntity.setAddress(addressEntity);
		CustomerEntity savedClient = clientRepository.save(customerEntity);
		return savedClient;
	}

	@Transactional
	public ResponseEntity<ResponseMessageDTO> update(Long clientId, CustomerDTO updatedClient, CustomerAddressDTO updatedAddress) {
		Optional<CustomerEntity> existingClient = clientRepository.findById(clientId);
		Optional<AddressEntity> existingAddress = addressRepository.findById(clientId);

		if (existingClient.isPresent()) {
			CustomerEntity clientToUpdate = existingClient.get();
			clientToUpdate.setPhone(updatedClient.getPhone());
		}

		if (existingAddress.isPresent()) {
			AddressEntity addressToUpdate = existingAddress.get();
			addressToUpdate.setCep(updatedAddress.getCep());
			addressToUpdate.setRoad(updatedAddress.getRoad());
			addressToUpdate.setNeighborhood(updatedAddress.getNeighborhood());
			addressToUpdate.setHousenumber(updatedAddress.getHousenumber());
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Dados atualizados com sucesso", this.getClass().getName(), null));
	}

	@Transactional
	public ResponseEntity<ResponseMessageDTO> delete(Long clientId) {
		Optional<CustomerEntity> existingClient = clientRepository.findById(clientId);

		if (existingClient.isPresent()) {
			clientRepository.delete(existingClient.get());
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Deletado com sucesso", this.getClass().getName(), null));
	}
}
