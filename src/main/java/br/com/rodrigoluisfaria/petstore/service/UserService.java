package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    void create(List<User> users);

    Optional<User> findByUsername(String username);

    void delete(String username);

    void update(String username, User user);

    Optional<User> searchByUsernameAndPassword(String username, String password);
}
