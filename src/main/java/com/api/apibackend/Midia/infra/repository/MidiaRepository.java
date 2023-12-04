package com.api.apibackend.Midia.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Midia.infra.entity.MidiaEntity;

@Repository
public interface MidiaRepository extends JpaRepository<MidiaEntity, Long> {
    
   List<MidiaEntity> findByCategory(String category);

   @Query("SELECT COUNT(m) FROM MidiaEntity m WHERE m.category = 'Salads'")
   Long countByCategorySalads();

   List<MidiaEntity> findAllByCategoryIn(List<String> categories);
}
