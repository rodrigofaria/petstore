package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;

public interface UserRepository {

    UserEntity create(UserEntity user);

    UserEntity findByUsername(String username);

    void delete(String username);

    void update(UserEntity user);

    UserEntity searchByUsernameAndPassword(String username, String password);

}
