package com.api.apibackend.Auth.Domain.service;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnonymizationService {
    private StringEncryptor encryptor;
    
    @Autowired
    public AnonymizationService(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String anonymizeCpf(String cpf) {
        int digitsToKeep = 4;
        return encryptor.encrypt(cpf.replaceAll("\\d(?=\\d{" + digitsToKeep + "})", "*"));
    }

    public String anonymizeNome(String nome) {
        return encryptor.encrypt("NomeAnonimo");
    }

    public String anonymizeEmail(String email) {
        return encryptor.encrypt("EmailAnonimo");
    }

    public String anonymizeCep(String cep) {
        int digitsToKeep = 2;
        return encryptor.encrypt(cep.substring(0, digitsToKeep) + cep.substring(digitsToKeep).replaceAll("\\d", "*"));
    }

    public String decrypt(String encryptedData) {
        return encryptor.decrypt(encryptedData);
    }
}
