package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.login.LoginService;
import br.com.rodrigoluisfaria.petstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final LoginService loginService;

    @Override
    public UserDto create(UserDto user) {
        UserEntity createdUser = userService.create(user.toUserEntity());
        return createdUser.toUserDto();
    }

    @Override
    public void createWithArray(List<UserDto> users) {
        userService.create(
                users.stream()
                        .map(user -> user.toUserEntity())
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void createWithList(List<UserDto> users) {
        userService.create(
                users.stream()
                        .map(user -> user.toUserEntity())
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void login(String username, String password) {
        loginService.doLogin(username, password);
    }

    @Override
    //@ResponseStatus(HttpStatus.)
    public void logout() {
        loginService.doLogout();
    }

    @Override
    public UserDto findByName(String username) {
        UserEntity user = userService.findByUsername(username);
        return user.toUserDto();
    }

    @Override
    public UserDto updateByName(String username, UserDto user) {
        userService.update(username, user.toUserEntity());
        return user;
    }

    @Override
    public void deleteByName(@PathVariable String username) {
        userService.delete(username);
    }
}
