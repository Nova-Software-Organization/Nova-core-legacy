package com.api.apibackend.modules.Midia.Domain.service;

import com.api.apibackend.modules.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.modules.Midia.Application.DTOs.ProductMidiaDTO;
import com.api.apibackend.modules.Midia.Domain.exception.ErrorValidationMidia;
import com.api.apibackend.modules.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.modules.Midia.infra.persistence.repository.MidiaRepository;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.repository.ProductRepository;
import com.api.apibackend.modules.Supplier.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Supplier.Domain.exception.ErrorValidationSupplier;
import com.api.apibackend.modules.Supplier.Infra.persistence.entity.SupplierEntity;
import com.api.apibackend.modules.SupplierAddress.infra.persistence.entity.SupplierAddressEntity;
import com.api.apibackend.shared.helpers.ModelMappersConvertion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class MidiaServiceCreated {
    private ProductRepository productRepository;

    @Autowired
    public MidiaServiceCreated(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<ResponseMessageDTO> create(MidiaDTO midiaRequest) {
        try {
            List<String> verificationMidia = this.validationMidiaRequest(midiaRequest);

            if (!verificationMidia.isEmpty()) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponseMessageDTO("Erro de validações na mídia", this.getClass().getName(), verificationMidia.toString()));
            }

            Optional<ProductEntity> productOptional = productRepository.findById(midiaRequest.getProductMidiaDTO().getIdProduct());
            if (productOptional.isPresent()) {
                ModelMappersConvertion<MidiaDTO, MidiaEntity> toDtoMidiaFromMidiaEntity = new ModelMappersConvertion<>(new ModelMapper());
                MidiaEntity newMidiaMappers = toDtoMidiaFromMidiaEntity.toDTOFromEntity(midiaRequest, MidiaEntity.class);
                ProductEntity newMidiaProduct = new ProductEntity();
                newMidiaProduct.setMidia(newMidiaMappers);

                productRepository.save(newMidiaProduct);
            return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseMessageDTO("Mídia adicionada com sucesso", this.getClass().getName(), null));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessageDTO("O produto com ID " + midiaRequest.getProductMidiaDTO().getIdProduct() + " não existe.", this.getClass().getName(), null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição", this.getClass().getName(), null));
        }
    }

    private List<String> validationMidiaRequest(MidiaDTO midiaDTO) {
        List<String> invalidMidia = new ArrayList<>();
        Optional.ofNullable(midiaDTO.getProductMidiaDTO())
                .map(ProductMidiaDTO::getIdProduct)
                .stream()
                .findFirst()
                .ifPresent(productId -> {
                    if (productId <= 0) {
                        invalidMidia.add("O ID do produto deve ser maior que zero.");
                    }
                });

        return invalidMidia;
    }
}
