/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.ProductCategory.Domain.repository;

import java.util.List;

import com.api.apibackend.modules.Product.Domain.model.Product;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;

public interface IProductCategoryService {
    public List<Product> getProductsByCategoryName(String categoryName);
	public Product convertToProductDTO(ProductEntity productEntity, ProductCategoryEntity category);
}
