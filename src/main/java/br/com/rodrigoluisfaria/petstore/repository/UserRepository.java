package br.com.rodrigoluisfaria.petstore.repository;

import br.com.rodrigoluisfaria.petstore.dto.User;
import br.com.rodrigoluisfaria.petstore.util.RandomGenerateIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private RandomGenerateIdentifier generateIdentifier;

    private final Map<String, User> myDatabase = new HashMap<>();

    public User create(User user) {
        user.setId(generateIdentifier.generateId());
        myDatabase.put(user.getUsername(), user);
        return user;
    }

    public User findByUsername(String username) {
        return myDatabase.get(username);
    }

    public void delete(String username) {
        myDatabase.remove(username);
    }

    public void update(User user) {
        myDatabase.put(user.getUsername(), user);
    }

    public User searchByUsernameAndPassword(String username, String password) {
        User user = myDatabase.get(username);
        if (user != null && !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}
