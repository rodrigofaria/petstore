package br.com.rodrigoluisfaria.petstore.service.user;

import br.com.rodrigoluisfaria.petstore.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;
import br.com.rodrigoluisfaria.petstore.service.user.exception.BadCredentialsException;
import br.com.rodrigoluisfaria.petstore.service.user.exception.UserNotFoundException;
import br.com.rodrigoluisfaria.petstore.service.user.exception.UsernameAlreadyExistException;
import br.com.rodrigoluisfaria.petstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user) throws AbstractUserServiceException {
        Optional<UserEntity> existUser = userRepository.usernameAlreadyExist(user.getUsername());
        if (existUser.isEmpty()) {
            return userRepository.create(user);
        }

        throw new UsernameAlreadyExistException(user.getUsername());
    }

    @Override
    public void create(List<UserEntity> users) throws AbstractUserServiceException {
        for (UserEntity userEntity : users) {
            create(userEntity);
        }
    }

    @Override
    public UserEntity findByUsername(String username) throws AbstractUserServiceException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException(username);
    }

    @Override
    public void delete(String username) throws AbstractUserServiceException {
        UserEntity user = findByUsername(username);
        userRepository.delete(user.getUuid());
    }

    @Override
    public void update(String username, UserEntity user) throws AbstractUserServiceException {
        UserEntity oldUser = findByUsername(username);
        user.setUuid(oldUser.getUuid());
        user.setUsername(username);
        userRepository.update(user);
    }

    @Override
    public UserEntity searchByUsernameAndPassword(String username, String password) throws AbstractUserServiceException {
        Optional<UserEntity> user = userRepository.searchByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return user.get();
        }

        throw new BadCredentialsException(username + " - " + password);
    }
}
