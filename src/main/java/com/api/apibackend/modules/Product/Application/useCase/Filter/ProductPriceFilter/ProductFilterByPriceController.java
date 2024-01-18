/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.ProductPriceFilter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Product.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

@Validated
@RestController
@RequestMapping("v1/produto")
public class ProductFilterByPriceController {
    private ProductFilterByPriceUseCase productFilterByPriceUseCase;

    @Autowired
    public ProductFilterByPriceController(ProductFilterByPriceUseCase productFilterByPriceUseCase) {
        this.productFilterByPriceUseCase = productFilterByPriceUseCase;
    }

    @GetMapping("/filtrarPorPreco")
    @Operation(summary = "Filtrar produtos por faixa de preço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos filtrados com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Erro de validação", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    public ResponseEntity<ResponseMessageDTO> filterByPriceRange(
            @Parameter(description = "Preço mínimo", required = true)
            @RequestParam @DecimalMin(value = "0.0", message = "O preço mínimo deve ser maior ou igual a 0.0") BigDecimal minPrice,

            @Parameter(description = "Preço máximo", required = true)
            @RequestParam @DecimalMax(value = "1000000.0", message = "O preço máximo deve ser menor ou igual a 1000000.0") BigDecimal maxPrice) {
        try {
            List<ProductEntity> filteredProducts = productFilterByPriceUseCase.execute(minPrice, maxPrice);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Sucesso!", this.getClass().getName(), filteredProducts, null));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("Erro de validação", this.getClass().getName(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }
}
