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

/**
 * Implementação em memória do repositório de usuários.
 */
@Component
public class IMemoryUserRepositoryTest implements IUserRepository {

    public EntityManager entityManager;

    public UserRepository userRepository;

    private final List<UserEntity> userEntityList = new ArrayList<>();

    /**
     * Construtor da classe IMemoryUserRepository.
     *
     * @param entityManager Gerenciador de entidades JPA injetado pelo Spring.
     * @param userRepository Repositório JPA para a entidade de usuário.
     */
    @Autowired
    public IMemoryUserRepositoryTest(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    /**
     * Persiste um novo usuário no banco de dados.
     *
     * @param userDTO DTO contendo os dados do usuário a ser persistido.
     * @return A entidade do usuário recém-persistida.
     */
    public UserEntity savePersist(UserDTO userDTO) {
        UserEntity newUser = new UserEntity(userDTO);
        this.entityManager.persist(newUser);
        return newUser;
    }

    /**
     * Cria um novo usuário na lista em memória.
     *
     * @param userDTO DTO contendo os dados do usuário a ser criado.
     * @return A entidade do usuário recém-criada em memória.
     */
    public UserEntity createUserInMemory(UserDTO userDTO) {
        UserEntity newUser = new UserEntity(userDTO);
        userEntityList.add(newUser);
        return newUser;
    }

    /**
     * Obtém todos os usuários na lista em memória.
     *
     * @return Lista contendo todas as entidades de usuário em memória.
     */
    public List<UserEntity> getAllUsersInMemory() {
        return new ArrayList<>(userEntityList);
    }
}
