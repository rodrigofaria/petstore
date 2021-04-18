package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create(UserEntity user);

    void create(List<UserEntity> users);

    UserEntity findByUsername(String username);

    void delete(String username);

    void update(String username, UserEntity user);

    UserEntity searchByUsernameAndPassword(String username, String password);
}
