package com.api.apibackend.Auth.Domain.service;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnonymizationService implements StringEncryptor {

    private StandardPBEStringEncryptor stringEncryptor;
    
    @Autowired
    public AnonymizationService(StandardPBEStringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public String anonymizeCpf(String cpf) {
        int digitsToKeep = 4;
        return stringEncryptor.encrypt(cpf.replaceAll("\\d(?=\\d{" + digitsToKeep + "})", "*"));
    }

    public String anonymizeNome(String nome) {
        return stringEncryptor.encrypt("NomeAnonimo");
    }

    public String anonymizeEmail(String email) {
        return stringEncryptor.encrypt("EmailAnonimo");
    }

    public String anonymizeCep(String cep) {
        int digitsToKeep = 2;
        return stringEncryptor.encrypt(cep.substring(0, digitsToKeep) + cep.substring(digitsToKeep).replaceAll("\\d", "*"));
    }

    @Override
    public String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }

    @Override
    public String decrypt(String encryptedData) {
        return stringEncryptor.decrypt(encryptedData);
    }
}
