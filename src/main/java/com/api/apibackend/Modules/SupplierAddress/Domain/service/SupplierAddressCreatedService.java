/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.SupplierAddress.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Supplier.Infra.entity.SupplierEntity;
import com.api.apibackend.Modules.Supplier.Infra.repository.SupplierRepository;
import com.api.apibackend.Modules.SupplierAddress.Application.DTOs.SupplierAddressDTO;
import com.api.apibackend.Modules.SupplierAddress.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;

@Service
public class SupplierAddressCreatedService {
	private final SupplierRepository supplierRepository;

	@Autowired
	public SupplierAddressCreatedService(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	public ResponseEntity<ResponseMessageDTO> create(SupplierAddressDTO supplierAddressDTO) {
		try {
			return Optional.ofNullable(supplierRepository.findById(supplierAddressDTO.getSupplierDTO().getIdSupplier()))
					.map(existingSupplier -> {
						boolean invalidSupplier = existingSupplier
								.map(supplier -> supplier.getSupplierAddressEntity().equals(supplierAddressDTO))
								.orElse(false);

						if (invalidSupplier) {
							return ResponseEntity.badRequest().body(new ResponseMessageDTO(
									"Fornecedor com o mesmo endereço informado já existe. Por favor, informe outro endereço para cadastro!",
									this.getClass().getName(), null));
						}

						SupplierAddressEntity newSupplierAddress = new SupplierAddressEntity();
						newSupplierAddress.setCep(supplierAddressDTO.getCep() != existingSupplier.get()
								.getSupplierAddressEntity().getCep() ? supplierAddressDTO.getCep()
										: existingSupplier.get().getSupplierAddressEntity().getCep());

						newSupplierAddress
								.setNeighborhood(supplierAddressDTO.getNeighborhood() != existingSupplier.get()
										.getSupplierAddressEntity().getNeighborhood()
												? supplierAddressDTO.getNeighborhood()
												: existingSupplier.get().getSupplierAddressEntity().getNeighborhood());

						newSupplierAddress.setNumberCompany(
								supplierAddressDTO.getNumberHouseOrCompany() != existingSupplier
										.get().getSupplierAddressEntity().getNumberCompany()
												? supplierAddressDTO.getNumberHouseOrCompany()
												: existingSupplier.get().getSupplierAddressEntity()
														.getNumberCompany());

						newSupplierAddress.setRoad(supplierAddressDTO.getRoad() != existingSupplier.get()
								.getSupplierAddressEntity().getRoad() ? supplierAddressDTO.getRoad()
										: existingSupplier.get().getSupplierAddressEntity().getRoad());

						SupplierEntity newSupplierEntity = new SupplierEntity();
						newSupplierEntity
								.setCnpj(supplierAddressDTO.getSupplierDTO().getCnpj() != existingSupplier.get()
										.getCnpj() ? supplierAddressDTO.getSupplierDTO().getCnpj()
												: existingSupplier.get().getCnpj());

						newSupplierEntity
								.setContact(supplierAddressDTO.getSupplierDTO().getContact() != existingSupplier.get()
										.getContact() ? supplierAddressDTO.getSupplierDTO().getContact()
												: existingSupplier.get().getContact());

						newSupplierEntity
								.setRegion(supplierAddressDTO.getSupplierDTO().getRegion() != existingSupplier.get()
										.getRegion() ? supplierAddressDTO.getSupplierDTO().getRegion()
												: existingSupplier.get().getRegion());

						newSupplierEntity
								.setNameCompany(supplierAddressDTO.getSupplierDTO().getNameCompany() != existingSupplier
										.get().getNameCompany() ? supplierAddressDTO.getSupplierDTO().getNameCompany()
												: existingSupplier.get().getNameCompany());

						newSupplierEntity.setOfficeSupplier(
								supplierAddressDTO.getSupplierDTO().getOfficeSupplier() != existingSupplier
										.get().getOfficeSupplier()
												? supplierAddressDTO.getSupplierDTO().getOfficeSupplier()
												: existingSupplier.get().getOfficeSupplier());

						newSupplierEntity
								.setStatus(supplierAddressDTO.getSupplierDTO().getStatus() != existingSupplier.get()
										.getStatus() ? supplierAddressDTO.getSupplierDTO().getStatus()
												: existingSupplier.get().getStatus());

						newSupplierEntity.setSupplierAddressEntity(newSupplierAddress);

						supplierRepository.save(newSupplierEntity);

						return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO(
								"Endereço adicionado ao fornecedor com sucesso! ", this.getClass().getName(), null));
					})
					.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(
							"Fornecedor não encontrado com o ID fornecido.", this.getClass().getName(), null)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
							e.getMessage()));
		}
	}
}
