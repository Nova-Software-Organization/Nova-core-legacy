/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Cart.Application.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Cart.Application.DTOs.CheckoutDTO;
import com.api.apibackend.modules.Cart.Application.DTOs.ProductCheckQuantity;
import com.api.apibackend.modules.Cart.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Cart.Application.DTOs.UnavailableProductsResponseDTO;
import com.api.apibackend.modules.Cart.Domain.exception.CartNotFoundException;
import com.api.apibackend.modules.Stock.Application.useCases.StockProduct.StockProductUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("v1/checkout")
public class CheckoutController {
    private StockProductUseCase stockProductUseCase;

    @Autowired
    public CheckoutController(StockProductUseCase stockProductUseCase) {
        this.stockProductUseCase = stockProductUseCase;
    }

    @PostMapping("/produto/disponivel")
    public ResponseEntity<UnavailableProductsResponseDTO> stockProduct(@RequestBody CheckoutDTO checkoutDTO) {
        try {
            List<ProductCheckQuantity> productCheckQuantities = checkoutDTO.getProductCheckQuantities();
            List<Long> unavailableProducts = stockProductUseCase.checkProductStock(productCheckQuantities);

            if (unavailableProducts.isEmpty()) {
                return ResponseEntity.ok().build(); // Todos os produtos têm estoque suficiente
            } else {
                ResponseMessageDTO responseMessage = new ResponseMessageDTO(
                        "Os seguintes produtos não estão disponíveis",
                        this.getClass().getName(),
                        null, unavailableProducts);
                UnavailableProductsResponseDTO responseDTO = new UnavailableProductsResponseDTO(unavailableProducts,
                        responseMessage);
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (CartNotFoundException e) {
            ResponseMessageDTO responseMessage = new ResponseMessageDTO(
                    "Erro ao verificar o estoque do produto",
                    this.getClass().getName(),
                    e.getMessage());
            UnavailableProductsResponseDTO responseDTO = new UnavailableProductsResponseDTO(null, responseMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}