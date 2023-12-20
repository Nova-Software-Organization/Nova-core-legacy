package com.api.apibackend.Customer.Application.DTOs.registration;

import lombok.Data;

@Data
public class CustomerDTO {
    private Boolean isAdmin;
    private String name;
    private String lastName;
    private String cpf;
    private String phone;
    private int age;
    private String gender;
    private String email;
    private String password;
}
