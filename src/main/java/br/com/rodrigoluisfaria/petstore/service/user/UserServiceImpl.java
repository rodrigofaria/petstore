package br.com.rodrigoluisfaria.petstore.service.user;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.exception.BadCredentialsException;
import br.com.rodrigoluisfaria.petstore.exception.UserNotFoundException;
import br.com.rodrigoluisfaria.petstore.exception.UsernameAlreadyExistException;
import br.com.rodrigoluisfaria.petstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserEntity create(UserEntity user) {
        Optional<UserEntity> existUser = userRepository.usernameAlreadyExist(user.getUsername());
        if (existUser.isEmpty()) {
            return userRepository.create(user);
        }

        throw new UsernameAlreadyExistException(user.getUsername());
    }

    public void create(List<UserEntity> users) {
        users.forEach(user -> create(user));
    }

    public UserEntity findByUsername(String username) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException(username);
    }

    public void delete(String username) {
        UserEntity user = findByUsername(username);
        userRepository.delete(user.getUuid());
    }

    public void update(String username, UserEntity user) {
        UserEntity oldUser = findByUsername(username);
        user.setUuid(oldUser.getUuid());
        user.setUsername(username);
        userRepository.update(user);
    }

    public UserEntity searchByUsernameAndPassword(String username, String password) {
        Optional<UserEntity> user = userRepository.searchByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return user.get();
        }

        throw new BadCredentialsException(username + " - " + password);
    }
}
