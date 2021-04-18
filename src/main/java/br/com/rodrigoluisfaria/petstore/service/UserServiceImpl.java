package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserEntity create(UserEntity user) {
        return userRepository.create(user);
    }

    public void create(List<UserEntity> users) {
        users.forEach(user -> create(user));
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(String username) {
        userRepository.delete(username);
    }

    public void update(String username, UserEntity user) {
        user.setUsername(username);
        userRepository.update(user);
    }

    public UserEntity searchByUsernameAndPassword(String username, String password) {
        return userRepository.searchByUsernameAndPassword(username, password);
    }
}
