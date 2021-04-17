package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.dto.User;

import java.util.Optional;

public interface UserRepository {

    User create(User user);

    Optional<User> findByUsername(String username);

    void delete(String username);

    void update(User user);

    Optional<User> searchByUsernameAndPassword(String username, String password);

}
