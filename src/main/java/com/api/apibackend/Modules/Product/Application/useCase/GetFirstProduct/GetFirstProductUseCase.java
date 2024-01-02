package com.api.apibackend.Modules.Product.Application.useCase.GetFirstProduct;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Modules.Product.Domain.model.Product;
import com.api.apibackend.Modules.Product.Domain.service.GetFirstProductService;

@Service
public class GetFirstProductUseCase {
    private GetFirstProductService getFirstProductService;

    @Autowired
    public GetFirstProductUseCase(GetFirstProductService getFirstProductService) {
        this.getFirstProductService = getFirstProductService;
    }

    public List<Product> execute() {
        return getFirstProductService.listProducts();
    }
}
