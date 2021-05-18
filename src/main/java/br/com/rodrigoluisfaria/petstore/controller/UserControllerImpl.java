package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.login.LoginService;
import br.com.rodrigoluisfaria.petstore.service.user.UserService;
import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping
    @Override
    public UserDto create(UserDto user) throws AbstractUserServiceException {
        log.debug("Creating user: {}", user);
        UserEntity createdUser = userService.create(user.toUserEntity());
        return createdUser.toUserDto();
    }

    @PostMapping("/createWithArray")
    @Override
    public void createWithArray(List<UserDto> users) throws AbstractUserServiceException {
        log.debug("Creating ({}) users", users.size());
        userService.create(
                users.stream()
                        .map(UserDto::toUserEntity)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/createWithList")
    @Override
    public void createWithList(List<UserDto> users) throws AbstractUserServiceException {
        log.debug("Creating ({}) users", users.size());
        userService.create(
                users.stream()
                        .map(UserDto::toUserEntity)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/login")
    @Override
    public void login(String username, String password) throws AbstractUserServiceException {
        log.debug("Executing login. username: [{}] & password: [{}]", username, password);
        loginService.doLogin(username, password);
    }

    @GetMapping("/logout")
    @Override
    public void logout() {
        log.info("Executing logout");
        loginService.doLogout();
    }

    @GetMapping("/{username}")
    @Override
    public UserDto findByName(String username) throws AbstractUserServiceException {
        log.debug("Finding user by username: [{}]", username);
        UserEntity user = userService.findByUsername(username);
        return user.toUserDto();
    }

    @PutMapping("/{username}")
    @Override
    public UserDto updateByName(String username, UserDto user) throws AbstractUserServiceException {
        log.debug("Update user by username: [{}] values = {}", username, user);
        userService.update(username, user.toUserEntity());
        return user;
    }

    @DeleteMapping("/{username}")
    @Override
    public void deleteByName(@PathVariable String username) throws AbstractUserServiceException {
        log.debug("Delete user by username: [{}]", username);
        userService.delete(username);
    }
}
