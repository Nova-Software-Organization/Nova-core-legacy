package com.api.apibackend.Modules.ProductCategory.Domain.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import com.api.apibackend.Modules.Product.Domain.model.Product;
import com.api.apibackend.Modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;

public interface IProductCategoryService {
    public List<Product> getProductsByCategoryName(String categoryName);
	public Product convertToProductDTO(ProductEntity productEntity, ProductCategoryEntity category);
}
