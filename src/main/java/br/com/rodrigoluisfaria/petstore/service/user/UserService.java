package br.com.rodrigoluisfaria.petstore.service.user;

import br.com.rodrigoluisfaria.petstore.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;

import java.util.List;

public interface UserService {

    UserEntity create(UserEntity user) throws AbstractUserServiceException;

    void create(List<UserEntity> users) throws AbstractUserServiceException;

    void update(String username, UserEntity user) throws AbstractUserServiceException;

    void delete(String username) throws AbstractUserServiceException;

    UserEntity findByUsername(String username) throws AbstractUserServiceException;

    UserEntity searchByUsernameAndPassword(String username, String password)  throws AbstractUserServiceException;
}
