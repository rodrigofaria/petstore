package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.exception.BadCredentialsException;
import br.com.rodrigoluisfaria.petstore.exception.UserNotFoundException;
import br.com.rodrigoluisfaria.petstore.exception.UsernameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, UserEntity> myDatabase = new HashMap<>();

    public UserEntity create(UserEntity user) {
        if (!usernameAlreadyExist(user.getUsername())) {
            myDatabase.put(user.getUuid(), user);
            return user;
        }

        throw new UsernameAlreadyExistException(user.getUsername());
    }

    public UserEntity findByUsername(String username) {
        return myDatabase.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new UserNotFoundException(username));
    }

    public void delete(String username) {
        UserEntity user = findByUsername(username);
        myDatabase.remove(user.getUuid());
    }

    public void update(UserEntity user) {
        UserEntity oldUser = findByUsername(user.getUsername());
        user.setUuid(oldUser.getUuid());
        myDatabase.put(user.getUuid(), user);
    }

    public UserEntity searchByUsernameAndPassword(String username, String password) {
        return myDatabase.values().stream()
                .filter(user -> user.getUsername().equals(username) &&
                                user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(()-> new BadCredentialsException(username + " - " + password));
    }

    private boolean usernameAlreadyExist(String username) {
        return myDatabase.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().isPresent();
    }
}
