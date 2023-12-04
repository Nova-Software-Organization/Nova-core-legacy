package com.api.apibackend.CustomerAddress.infra.entity;

import java.io.Serializable;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "Endereco")
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idendereco")
    private Long id;

    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private CustomerEntity customerEntity;
    
    @NotBlank(message = "A rua não pode estar em branco")
    @Column(name = "rua")
    private String road;
    
    @NotBlank(message = "O bairro não pode estar em branco")
    @Column(name = "bairro")
    private String neighborhood;

    @NotBlank(message = "O número da casa não pode estar em branco")
    @Column(name = "numCasa")
    private String housenumber;

    @Column(name = "estado")
    private String state;

    @NotBlank(message = "O cep não pode estar em branco")
    @Column(name = "cep")
    private String cep;

    public AddressEntity() { }

    @JsonProperty("client")
    public CustomerEntity getcustomerEntity() {
        return this.customerEntity;
    }

    public boolean isSameAddress(AddressEntity other) {
        return this.getRoad().equals(other.getRoad()) &&
               this.getNeighborhood().equals(other.getNeighborhood()) &&
               this.getHousenumber() == other.getHousenumber() &&
               this.getState().equals(other.getState()) &&
               this.getCep().equals(other.getCep());
    }
}
