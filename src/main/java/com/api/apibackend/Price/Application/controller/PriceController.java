/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Price.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.Price.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.Price.Application.useCases.PriceCreated.PriceAddUseCase;
import com.api.apibackend.Price.Application.useCases.PriceDelete.PriceDeleteUseCase;
import com.api.apibackend.Price.Application.useCases.PriceUpdate.PriceUpdateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/preco")
@Tag(name = "Preço", description = "Operações relacionadas a preços de produtos")
public class PriceController {
	private final PriceAddUseCase priceAddUseCase;
	private final PriceUpdateUseCase priceUpdateUseCase;
	private final PriceDeleteUseCase priceDeleteUseCase;

	@Autowired
	public PriceController(PriceAddUseCase priceAddUseCase, PriceUpdateUseCase priceUpdateUseCase,
			PriceDeleteUseCase priceDeleteUseCase) {
		this.priceAddUseCase = priceAddUseCase;
		this.priceUpdateUseCase = priceUpdateUseCase;
		this.priceDeleteUseCase = priceDeleteUseCase;
	}

	@PostMapping("/adicionar")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Adicionar Preço", description = "Adiciona um novo preço para um produto no banco de dados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Preço adicionado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> addPrice(@PathVariable Long id, @RequestBody PriceDTO priceDTO) {
		if (priceDTO == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseMessageDTO("dados do preço inválido", this.getClass().getName(), null));
		}

		if (id == null || id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseMessageDTO("id do produto inválido", this.getClass().getName(), null));
		}

		return priceAddUseCase.execute(id, priceDTO);
	}

	@PostMapping("/atualizar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Atualizar Preço", description = "Atualiza o preço de um produto no banco de dados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Preço atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> updatePrice(@PathVariable Long id, @RequestBody PriceDTO priceDTO) {
		try {
			if (priceDTO == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ResponseMessageDTO("Preço inválido", this.getClass().getName(), null));
			}

			return priceUpdateUseCase.execute(id, priceDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
							e.getMessage()));
		}
	}

	@PostMapping("/deletar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Deletar Preço", description = "Deleta o preço de um produto do banco de dados.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Preço deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> deletePrice(@PathVariable Long id) {
		try {
			if (id == null || id <= 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ResponseMessageDTO("ID do produto inválido", this.getClass().getName(), null));
			}

			return priceDeleteUseCase.execute(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(),
							e.getMessage()));
		}
	}
}
