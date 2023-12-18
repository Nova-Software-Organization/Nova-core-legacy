package com.api.apibackend.Product.Infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.ProductCategory.infra.entity.ProductCategoryEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p JOIN p.category c WHERE c.name = :categoryName")
    List<ProductEntity> findByCategoryName(@Param("categoryName") String categoryName);

    List<ProductEntity> findByCategory(ProductCategoryEntity category);
}
