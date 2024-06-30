/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerFIndByName;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Customer.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Customer.Domain.exception.CustomerNotFoundException;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@RestController
@RequestMapping("v1/cliente")
public class CustomerFindByNameController {
    
    private CustomerFindByNameUseCase customerFindByNameUseCase;

    @Autowired
    public CustomerFindByNameController(CustomerFindByNameUseCase customerFindByNameUseCase) {
        this.customerFindByNameUseCase = customerFindByNameUseCase;
    }
    

    @GetMapping("/pesquisarPorNome")
    public ResponseEntity<ResponseMessageDTO> handle(@RequestParam(name = "nome") String name) {
        try {
            List<CustomerEntity> customers = customerFindByNameUseCase.execute(name);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Sucesso!", this.getClass().getName(), customers, null));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO("Usuário não encontrado", this.getClass().getName(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessageDTO("Ocorreu um erro ao processar a requisição!", this.getClass().getName(), e.getMessage()));
        }
    }
}
