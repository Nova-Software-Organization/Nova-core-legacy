package com.api.apibackend.modules.Product.Domain.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import com.api.apibackend.modules.Product.Domain.model.Product;

public interface IGetFirstService {
	public List<Product> listProducts();
}
