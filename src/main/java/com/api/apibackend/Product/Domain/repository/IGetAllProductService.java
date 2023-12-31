package com.api.apibackend.Product.Domain.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import com.api.apibackend.Product.Domain.model.Product;
import com.api.apibackend.Product.Infra.entity.ProductEntity;

public interface IGetAllProductService {
    List<Product> getAllProductsFromCache();
    Product mapToProduct(ProductEntity product);
    List<Product> listProducts();
}
