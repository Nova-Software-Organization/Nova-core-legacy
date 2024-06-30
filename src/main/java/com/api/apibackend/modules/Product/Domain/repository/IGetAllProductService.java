/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.repository;

import java.util.List;

import com.api.apibackend.modules.Product.Domain.model.Product;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

public interface IGetAllProductService {
    List<Product> getAllProductsFromCache();
    Product mapToProduct(ProductEntity product);
    List<Product> listProducts();
}
