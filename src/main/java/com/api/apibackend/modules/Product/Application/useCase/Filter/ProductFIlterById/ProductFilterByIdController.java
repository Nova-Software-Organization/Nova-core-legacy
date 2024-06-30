/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.ProductFIlterById;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Product.Domain.model.Product;
import com.api.apibackend.modules.Product.Domain.service.GetAllProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/produto")
public class ProductFilterByIdController {
	private GetAllProductService getAllProductsService;

	@Autowired
	public ProductFilterByIdController(GetAllProductService getAllProductsService) {
		this.getAllProductsService = getAllProductsService;
	}

	@GetMapping("/pesquisar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Busca por um produto pelo ID", description = "Busca pelo produto pelo ID passado na requisição")
	@Operation(summary = "Rota responsável por buscar produtos no banco de dados pelo ID")
	@SecurityRequirement(name = "jwt_auth")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Produtos filtrados com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "403", description = "Acesso negado", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
	})
	public ResponseEntity<List<Product>> handle(@Parameter(description = "ID do produto a ser pesquisado", required = true)
	@PathVariable Long id) {
		List<Product> products = getAllProductsService.listProducts();
		List<Product> filteredProducts = products.stream()
				.filter(product -> product.getId().equals(id))
				.collect(Collectors.toList());

		return ResponseEntity.ok(filteredProducts);
	}
}
