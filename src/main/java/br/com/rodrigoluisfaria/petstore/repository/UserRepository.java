package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    UserEntity create(UserEntity user);

    void update(UserEntity user);

    void delete(String uuid);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> searchByUsernameAndPassword(String username, String password);

    Optional<UserEntity> usernameAlreadyExist(String username);
}
