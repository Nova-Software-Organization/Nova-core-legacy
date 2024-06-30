/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Midia.Application.useCases.CreatedMidia;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/midia")
public class MidiaCreatedController {

    private MidiaUseCaseCreated midiaUseCaseCreated;

    @Autowired
    public MidiaCreatedController(MidiaUseCaseCreated midiaUseCaseCreated) {
        this.midiaUseCaseCreated = midiaUseCaseCreated;
    }

    @PostMapping(path = "/adicionar")
    @Tag(name = "Midia", description = "Operações relacionadas a imagem do produto")
    @Operation(summary = "Adiciona uma nova imagem a um produto")
    public ResponseEntity<ResponseMessageDTO> handle(
            @RequestBody MidiaDTO midiaDTO) {
        try {
            Optional.ofNullable(midiaDTO).orElseThrow();
            return midiaUseCaseCreated.execute(midiaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(
                    "ocorreu um erro ao processar a requisição", this.getClass().getName(), e.getMessage()));
        }
    }
}
