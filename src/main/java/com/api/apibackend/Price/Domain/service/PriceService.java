package com.api.apibackend.Price.Domain.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.apibackend.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.Price.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.Price.infra.repository.PriceRepository;
import com.api.apibackend.Product.Infra.entity.ProductEntity;
import com.api.apibackend.Product.Infra.repository.ProductRepository;

public class PriceService {
    private PriceRepository priceRepository;
    private ProductRepository productRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository, ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<ResponseMessageDTO> addPriceProduct(Long id, PriceDTO priceDTO) {
        try {
            Optional<ProductEntity> searchProduct = productRepository.findById(id);
            
            if (!searchProduct.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessageDTO("Produto não encontrado!", this.getClass().getName(), null));
            }
            
            ProductEntity product = searchProduct.get();
            PriceEntity price = new PriceEntity();
            price.setCurrency(priceDTO.getCurrency());
            price.setDiscountPrice(priceDTO.getDiscountPrice());
            price.setEndDate(priceDTO.getEndDate());
            price.setPriceOrigin(priceDTO.getPriceOrigin());
            price.setNotes(priceDTO.getNotes());
            price.setStartDate(null);
            price.setProductEntity(product);
            price.setStatus(priceDTO.getStatus());
            price.setUnitOfMeasure(priceDTO.getUnitOfMeasure());
            price.setUpdatedBy(null);

            priceRepository.save(price);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseMessageDTO("Preço adicionado com sucesso!", this.getClass().getName(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }

    public ResponseEntity<ResponseMessageDTO> updatePriceProduct(Long id, PriceDTO priceDTO) {
        try {
            ResponseEntity<ResponseMessageDTO> validationResponse = validatePriceDTO(priceDTO);
            if (validationResponse != null) {
                return validationResponse;
            }
    
            Optional<ProductEntity> searchProduct = productRepository.findById(id);
            if (!searchProduct.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMessageDTO("Produto não encontrado!", this.getClass().getName(), null));
            }
    
            ProductEntity product = searchProduct.get();
    
            Optional<PriceEntity> existingPrice = priceRepository.findByProductEntityAndStatus(product, priceDTO.getStatus());
            if (existingPrice.isPresent()) {
                PriceEntity price = existingPrice.get();
                price.setCurrency(priceDTO.getCurrency());
                price.setDiscountPrice(priceDTO.getDiscountPrice());
                price.setEndDate(priceDTO.getEndDate());
                price.setPriceOrigin(priceDTO.getPriceOrigin());
                price.setNotes(priceDTO.getNotes());
                price.setStartDate(null);
                price.setProductEntity(product);
                price.setStatus(priceDTO.getStatus());
                price.setUnitOfMeasure(priceDTO.getUnitOfMeasure());
                price.setUpdatedBy(null);
    
                priceRepository.save(price);
    
                return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseMessageDTO("Preço atualizado com sucesso!", this.getClass().getName(), null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMessageDTO("Preço não encontrado para o produto!", this.getClass().getName(), null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }

    private ResponseEntity<ResponseMessageDTO> validatePriceDTO(PriceDTO priceDTO) {
        if (priceDTO == null) {
            return ResponseEntity.badRequest().body(
                new ResponseMessageDTO("DTO de preço não fornecido!", this.getClass().getName(), null));
        }

        if (priceDTO.getDiscountPrice().intValue() < 0 || priceDTO.getPrice().intValue() < 0) {
            return ResponseEntity.badRequest().body(
                new ResponseMessageDTO("Valores de preço inválidos!", this.getClass().getName(), null));
        }

        if (priceDTO.getNotes() != null && priceDTO.getNotes().length() > 100) {
            return ResponseEntity.badRequest().body(
                new ResponseMessageDTO("Notas de preço excedem o comprimento permitido!", this.getClass().getName(), null));
        }

        return null;
    }

    public ResponseEntity<ResponseMessageDTO> deletePrice(Long id) {
        try {
            Optional<ProductEntity> searchProduct = productRepository.findById(id);
            if (!searchProduct.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseMessageDTO("Produto não encontrado! preço não pode ser deletado!", this.getClass().getName(), null));
            }
    
            PriceEntity productAndPrice = searchProduct.get().getPriceEntity();
            priceRepository.delete(productAndPrice);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMessageDTO("Preço deletado com sucesso!", this.getClass().getName(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }
}
