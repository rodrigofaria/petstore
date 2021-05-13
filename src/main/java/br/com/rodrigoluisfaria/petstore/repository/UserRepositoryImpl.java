package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, UserEntity> myDatabase = new HashMap<>();

    public UserEntity create(UserEntity user) {
        myDatabase.put(user.getUuid(), user);
        return user;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return myDatabase.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public void delete(String uuid) {
        myDatabase.remove(uuid);
    }

    public void update(UserEntity user) {
        myDatabase.put(user.getUuid(), user);
    }

    public Optional<UserEntity> searchByUsernameAndPassword(String username, String password) {
        return myDatabase.values().stream()
                .filter(user -> sameUsernameAndPassword(user, username, password))
                .findFirst();
    }

    public Optional<UserEntity> usernameAlreadyExist(String username) {
        return myDatabase.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    private boolean sameUsernameAndPassword(UserEntity user, String username, String password) {
        return user.getUsername().equals(username) &&
                user.getPassword().equals(password);
    }
}
