package br.com.rodrigoluisfaria.petstore.service.user;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create(UserEntity user);

    void create(List<UserEntity> users);

    void update(String username, UserEntity user);

    void delete(String username);

    UserEntity findByUsername(String username);

    UserEntity searchByUsernameAndPassword(String username, String password);
}
