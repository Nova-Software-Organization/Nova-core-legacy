/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Domain.model.Product;

public interface IProductController {
	ResponseEntity<?> populationCreationProduct(@RequestBody List<ProductDTO> productDTOList);
	ResponseEntity<List<Product>> getFirstProducts();
	ResponseEntity<List<Product>> getAllProductsEndpoint();
}
