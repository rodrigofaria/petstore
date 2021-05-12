package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.login.LoginService;
import br.com.rodrigoluisfaria.petstore.service.user.UserService;
import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping
    @Override
    public UserDto create(UserDto user) throws AbstractUserServiceException {
        UserEntity createdUser = userService.create(user.toUserEntity());
        return createdUser.toUserDto();
    }

    @PostMapping("/createWithArray")
    @Override
    public void createWithArray(List<UserDto> users) throws AbstractUserServiceException {
        userService.create(
                users.stream()
                        .map(UserDto::toUserEntity)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/createWithList")
    @Override
    public void createWithList(List<UserDto> users) throws AbstractUserServiceException {
        userService.create(
                users.stream()
                        .map(UserDto::toUserEntity)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/login")
    @Override
    public void login(String username, String password) throws AbstractUserServiceException {
        loginService.doLogin(username, password);
    }

    @GetMapping("/logout")
    @Override
    public void logout() {
        loginService.doLogout();
    }

    @GetMapping("/{username}")
    @Override
    public UserDto findByName(String username) throws AbstractUserServiceException {
        UserEntity user = userService.findByUsername(username);
        return user.toUserDto();
    }

    @PutMapping("/{username}")
    @Override
    public UserDto updateByName(String username, UserDto user) throws AbstractUserServiceException {
        userService.update(username, user.toUserEntity());
        return user;
    }

    @DeleteMapping("/{username}")
    @Override
    public void deleteByName(@PathVariable String username) throws AbstractUserServiceException {
        userService.delete(username);
    }
}
