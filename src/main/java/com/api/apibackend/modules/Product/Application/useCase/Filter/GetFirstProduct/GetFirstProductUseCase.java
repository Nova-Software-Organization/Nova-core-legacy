/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.useCase.Filter.GetFirstProduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Product.Domain.model.Product;
import com.api.apibackend.modules.Product.Domain.service.GetFirstProductService;

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
