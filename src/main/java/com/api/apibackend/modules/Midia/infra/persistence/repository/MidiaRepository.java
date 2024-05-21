/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Midia.infra.persistence.repository;

import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MidiaRepository extends JpaRepository<MidiaEntity, Long> {
   List<MidiaEntity> findByCategory(String category);
   List<MidiaEntity> findAllByCategoryIn(List<String> categories);
   List<MidiaEntity> findByUrls(String url);
   MidiaEntity findByUrl(String url);
}
