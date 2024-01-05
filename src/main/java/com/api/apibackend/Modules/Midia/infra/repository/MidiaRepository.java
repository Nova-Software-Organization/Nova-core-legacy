/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Midia.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.apibackend.Modules.Midia.infra.persistence.entity.MidiaEntity;

@Repository
public interface MidiaRepository extends JpaRepository<MidiaEntity, Long> {
    
   List<MidiaEntity> findByCategory(String category);

   @Query("SELECT COUNT(m) FROM MidiaEntity m WHERE m.category = 'Salads'")
   Long countByCategorySalads();

   List<MidiaEntity> findAllByCategoryIn(List<String> categories);
}
