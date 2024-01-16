package com.api.apibackend.modules.Product.Infra.persistence.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p JOIN p.category c WHERE c.name = :categoryName")
    List<ProductEntity> findByCategoryName(@Param("categoryName") String categoryName);
    @Query("SELECT p FROM ProductEntity p WHERE p.idProduct IN :ids")
    List<ProductEntity> findByIds(@Param("ids") List<Long> ids);
    List<ProductEntity> findByCategory(ProductCategoryEntity category);
    Optional<ProductEntity> findByName(String name);
    List<ProductEntity> findBySupplierEntity(SupplierEntity supplierEntity);
    List<ProductEntity> findByStatus(int status);
    List<ProductEntity> findBySku(String sku);
}
