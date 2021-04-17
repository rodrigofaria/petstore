package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.dto.User;
import br.com.rodrigoluisfaria.petstore.exception.UserNotFoundException;
import br.com.rodrigoluisfaria.petstore.exception.UsernameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> myDatabase = new HashMap<>();

    public User create(User user) {
        if (!myDatabase.values().contains(user)) {
            myDatabase.put(user.getUuid(), user);
            return user;
        }

        throw new UsernameAlreadyExistException(user.getUsername());
    }

    public Optional<User> findByUsername(String username) {
        return myDatabase.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public void delete(String username) {
        Optional<User> user = findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        myDatabase.remove(user.get().getUuid());
    }

    public void update(User user) {
        Optional<User> oldUser = findByUsername(user.getUsername());
        if (oldUser.isEmpty()) {
            throw new UserNotFoundException(user.getUsername());
        }
        user.setUuid(oldUser.get().getUuid());
        myDatabase.put(user.getUuid(), user);

    }

    public Optional<User> searchByUsernameAndPassword(String username, String password) {
        return myDatabase.values().stream()
                .filter(user -> user.getUsername().equals(username) &&
                                user.getPassword().equals(password))
                .findFirst();
    }
}
