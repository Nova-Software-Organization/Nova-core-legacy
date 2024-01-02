package com.api.apibackend.Modules.Product.Application.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.Modules.Product.Domain.model.Product;

public interface IProductController {
	ResponseEntity<?> populationCreationProduct(@RequestBody List<ProductDTO> productDTOList);
	ResponseEntity<List<Product>> getFirstProducts();
	public ResponseEntity<List<Product>> getAllProductsEndpoint();
}
