package br.com.rodrigoluisfaria.petstore.service.user;

import br.com.rodrigoluisfaria.petstore.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;
import br.com.rodrigoluisfaria.petstore.service.user.exception.BadCredentialsException;
import br.com.rodrigoluisfaria.petstore.service.user.exception.UserNotFoundException;
import br.com.rodrigoluisfaria.petstore.service.user.exception.UsernameAlreadyExistException;
import br.com.rodrigoluisfaria.petstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user) throws AbstractUserServiceException {
        Optional<UserEntity> existUser = userRepository.usernameAlreadyExist(user.getUsername());
        log.debug("Checking username: {}", existUser);

        if (existUser.isEmpty()) {
            log.info("Creating user");
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
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public void delete(String username) throws AbstractUserServiceException {
        UserEntity user = findByUsername(username);
        log.info("Deleting user");
        log.debug("User: {}", user);
        userRepository.delete(user.getUuid());
    }

    @Override
    public void update(String username, UserEntity user) throws AbstractUserServiceException {
        UserEntity oldUser = findByUsername(username);
        user.setUuid(oldUser.getUuid());
        user.setUsername(username);
        log.info("Updating user");
        log.debug("User before update process: {}", oldUser);
        log.debug("User after update process: {}", user);
        userRepository.update(user);
    }

    @Override
    public UserEntity searchByUsernameAndPassword(String username, String password) throws AbstractUserServiceException {
        return userRepository.searchByUsernameAndPassword(username, password)
                .orElseThrow(() -> new BadCredentialsException(username + " - " + password));
    }
}
