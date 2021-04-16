package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.dto.User;
import br.com.rodrigoluisfaria.petstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.create(user);
    }

    public void create(List<User> users) {
        for (User user : users) {
            create(user);
        }
    }

    public Optional<User> findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public void delete(String username) {
        userRepository.delete(username);
    }

    public void update(String username, User user) {
        user.setUsername(username);
        userRepository.update(user);
    }

    public Optional<User> searchByUsernameAndPassword(String username, String password) {
        User user = userRepository.searchByUsernameAndPassword(username, password);
        if (user != null) {
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
