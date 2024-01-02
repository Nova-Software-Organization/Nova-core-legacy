package com.api.apibackend.Modules.Product.Domain.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import com.api.apibackend.Modules.Product.Domain.model.Product;

public interface IGetFirstService {
	public List<Product> listProducts();
}
