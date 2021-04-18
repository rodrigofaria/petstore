package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.dto.User;
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

    private final Map<String, User> myDatabase = new HashMap<>();

    public User create(User user) {
        if (!myDatabase.values().contains(user)) {
            myDatabase.put(user.getUuid(), user);
            return user;
        }

        throw new UsernameAlreadyExistException(user.getUsername());
    }

    public User findByUsername(String username) {
        return myDatabase.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new UserNotFoundException(username));
    }

    public void delete(String username) {
        User user = findByUsername(username);
        myDatabase.remove(user.getUuid());
    }

    public void update(User user) {
        User oldUser = findByUsername(user.getUsername());
        user.setUuid(oldUser.getUuid());
        myDatabase.put(user.getUuid(), user);
    }

    public User searchByUsernameAndPassword(String username, String password) {
        return myDatabase.values().stream()
                .filter(user -> user.getUsername().equals(username) &&
                                user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(()-> new BadCredentialsException(username + " - " + password));
    }
}
