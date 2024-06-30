/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerGetAll;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Customer.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@RestController
@RequestMapping("v1/clientes")
public class CustomerGetAllController {
    private CustomerGetAllUseCase customerGetAllUseCase;

    @GetMapping
    public ResponseEntity<ResponseMessageDTO> handle() {
        try {
            List<CustomerEntity> customers = customerGetAllUseCase.execute();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Sucesso!", this.getClass().getName(), customers, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }
}
