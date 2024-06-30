package com.api.apibackend.modules.Auth.Application.services;

public interface IAnonymizationService {
  String anonymizeCpf(String cpf);
  String anonymizeEmail(String email);
  String anonymizeCep(String cep);
  String encrypt(String data);
  String decrypt(String encryptedData);
}
