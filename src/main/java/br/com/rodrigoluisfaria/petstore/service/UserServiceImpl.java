package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.dto.User;
import br.com.rodrigoluisfaria.petstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.create(user);
    }

    public void create(List<User> users) {
        users.forEach(user -> create(user));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(String username) {
        userRepository.delete(username);
    }

    public void update(String username, User user) {
        user.setUsername(username);
        userRepository.update(user);
    }

    public User searchByUsernameAndPassword(String username, String password) {
        return userRepository.searchByUsernameAndPassword(username, password);
    }
}
