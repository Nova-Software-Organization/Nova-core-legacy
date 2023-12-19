package com.api.apibackend.Customer.Infra.persistence.entity;

import java.io.Serializable;
import java.util.List;

import com.api.apibackend.Auth.Infra.entity.UserEntity;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;
import com.api.apibackend.Order.infra.entity.OrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "cliente")
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idendereco")
    @JsonManagedReference
    private AddressEntity address;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 50, message = "O nome deve ter no máximo 255 caracteres")
    @Column(name = "nome")
    private String name;

    @Size(max = 100, message = "O sobrenome deve ter no máximo 255 caracteres")
    @Column(name = "sobrenome")
    private String lastName;

    @NotBlank(message = "O cpf não pode estar em branco")
    @Size(max = 11, message = "O CPF deve ter no maximo 11 caracteres")
    @Column(name = "cpf")
    private String cpf;
    
    @Size(max = 11, message = "O telefone deve ter no máximo 20 caracteres")
    @Column(name = "telefone")
    private String phone;
    
    @Max(value = 100, message = "A idade deve ser menor ou igual a 150")
    @Column(name = "idade")
    private int age;
    
    @Size(max = 12, message = "O gênero deve ter no máximo 20 caracteres")
    @Column(name = "genero")
    private String gender;
    
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "O email deve ser válido")
    @Size(max = 100, message = "O email deve ter no máximo 255 caracteres")
    @Column(name = "email", unique = true)
    private String email;
    
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(max = 200, message = "O senha deve ter no máximo 8 caracteres")
    @Column(name = "senha")
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @OneToMany(mappedBy = "client")
    private List<OrderEntity> orders;
}
