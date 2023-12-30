package com.api.apibackend.Product.Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Product.Application.repository.IProductController;
import com.api.apibackend.Product.Application.useCase.GetAllProductUseCase;
import com.api.apibackend.Product.Application.useCase.GetFirstProductUseCase;
import com.api.apibackend.Product.Application.useCase.ProductAddUseCase;
import com.api.apibackend.Product.Application.useCase.ProductDeactivateUseCase;
import com.api.apibackend.Product.Application.useCase.ProductDeleteUseCase;
import com.api.apibackend.Product.Application.useCase.ProductUpdateUseCase;
import com.api.apibackend.Product.Application.useCase.ProductUseCaseCreated;
import com.api.apibackend.Product.Domain.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("v1/produto")
public class ProductController implements IProductController {
	private ProductAddUseCase productUseCase;
	private GetAllProductUseCase getAllProductUseCase;
	private GetFirstProductUseCase getFirstProductUseCase;
	private ProductUseCaseCreated productUseCaseCreated;
	private ProductDeactivateUseCase productDeactivateUseCase;
	private ProductDeleteUseCase productDeleteUseCase;
	private ProductUpdateUseCase productUpdateUseCase;
		
	@Autowired
	public ProductController(
		ProductAddUseCase productUseCase,
		GetAllProductUseCase getAllProductUseCase,
		GetFirstProductUseCase getFirstProductUseCase,
		ProductUseCaseCreated productUseCaseCreated,
		ProductDeactivateUseCase productDeactivateUseCase,
		ProductDeleteUseCase productDeleteUseCase,
		ProductUpdateUseCase productUpdateUseCase
	) {
		this.productUseCase = productUseCase;
		this.getAllProductUseCase = getAllProductUseCase;
		this.getFirstProductUseCase = getFirstProductUseCase;
		this.productUseCaseCreated = productUseCaseCreated;
		this.productDeactivateUseCase = productDeactivateUseCase;
		this.productDeleteUseCase = productDeleteUseCase;
		this.productUpdateUseCase = productUpdateUseCase;
	}
	
	@PostMapping("/adicionar")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Adiciona produtos", description = "Adiciona produtos dentro banco de dados")
	@Operation(summary = "Rota responsavel por adicionar produtos no banco dados caso aja necessidade de adicionar pela própria aplicação central, Nova-core")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> populationCreationProduct(@RequestBody List<ProductDTO> productDTOList) {
		try {
            if (productDTOList == null || productDTOList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Lista de produtos vazia ou nula", this.getClass().getName(), null));
            }

            return productUseCase.execute(productDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), null));
        }
	}

	@PostMapping("/unico/produto")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Adiciona um unico produto", description = "Adiciona produtos dentro banco de dados")
	@Operation(summary = "Rota responsavel por adicionar produtos no banco dados caso aja necessidade de adicionar pela própria aplicação central, Nova-core")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> create(@RequestBody ProductDTO productDTO) {
		return productUseCaseCreated.execute(productDTO);
	}

	@PostMapping("/atualizar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Adiciona um unico produto", description = "Adiciona produtos dentro banco de dados")
	@Operation(summary = "Rota responsavel por atualizar os produtos no banco dados!")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		try {
            if (productDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("Produto inválido", this.getClass().getName(), null));
            }

            return productUseCaseCreated.execute(productDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
	}
	

	@PostMapping("/deletar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Adiciona um unico produto", description = "Adiciona produtos dentro banco de dados")
	@Operation(summary = "Rota responsavel por deletar os produtos do banco dados caso aja necessidade!")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> delete(@PathVariable Long id) {
		try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID do produto inválido", this.getClass().getName(), null));
            }

            return productDeleteUseCase.execute(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
	}

	@PostMapping("/produto/desativar/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Desativa um produto", description = "Desativa o produto")
	@Operation(summary = "Rota responsavel por desativar um produto e não trazer o produto na consulta")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<ResponseMessageDTO> deactivate(@PathVariable Long id) {
		try {
            if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO("ID do produto inválido", this.getClass().getName(), null));
            }

            return productDeactivateUseCase.execute(id);
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
	}

	@GetMapping("/todos")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Lista os produtos", description = "Lista todos os produtos dentro do banco de dados da respectiva loja")
	@Operation(summary = "Rota responsável por listar todos os produtos do banco dados caso aja necessidade")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> getAllProductsEndpoint() {
		List<Product> products = getAllProductUseCase.execute();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/limitado")
	@PreAuthorize("hasRole('ADMIN')")
	@Tag(name = "Lista os produtos de maneira limitada", description = "Lista os produtos dentro do banco de dados pegando os 12 primeiros")
	@Operation(summary = "Rota responsável por listar os 12 primeiros produtos do banco de dados")
	@SecurityRequirement(name = "jwt_auth")
	public ResponseEntity<List<Product>> getFirstProducts() {
		List<Product> products = getFirstProductUseCase.execute();
		return ResponseEntity.ok(products);
	}
}
