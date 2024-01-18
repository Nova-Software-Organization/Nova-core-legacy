/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.modules.Product.Application.DTOs.ProductDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;

public interface IProductConventionalService {
     ResponseEntity<ResponseMessageDTO> populationProduct(List<ProductDTO> productDTOList);
}
