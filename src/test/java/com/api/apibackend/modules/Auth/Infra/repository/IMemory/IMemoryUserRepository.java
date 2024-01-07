/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Infra.repository.IMemory;

import com.api.apibackend.modules.Auth.Application.DTOs.UserDTO;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IMemoryUserRepository implements IUserRepository {

    public EntityManager entityManager;
    public UserRepository userRepository;
    private final List<UserEntity> userEntityList = new ArrayList<>();


    @Autowired
    public IMemoryUserRepository(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    public UserEntity savePersist(UserDTO userDTO) {
        UserEntity newUser = new UserEntity(userDTO);
        this.entityManager.persist(newUser);
        return newUser;
    }

    public UserEntity createUserInMemory(UserDTO userDTO) {
        UserEntity newUser = new UserEntity(userDTO);
        userEntityList.add(newUser);
        return newUser;
    }

    public List<UserEntity> getAllUsersInMemory() {
        return new ArrayList<>(userEntityList);
    }
}
