package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.LoginService;
import br.com.rodrigoluisfaria.petstore.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto user) {
        UserEntity createdUser = userService.create(user.toUserEntity());
        return ResponseEntity.status(201).body(createdUser.toUserDto());
    }

    @PostMapping("/createWithArray")
    public ResponseEntity<UserDto> createWithArray(@RequestBody List<UserDto> users) {
        userService.create(
                users.stream()
                        .map(user -> user.toUserEntity())
                        .collect(Collectors.toList())
        );
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/createWithList")
    public ResponseEntity<UserDto> createWithList(@RequestBody List<UserDto> users) {
        userService.create(
                users.stream()
                        .map(user -> user.toUserEntity())
                        .collect(Collectors.toList())
        );
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/login")
    public ResponseEntity<UserDto> login(@RequestParam(name = "username") String username,
                                         @RequestParam(name = "password") String password) {
        boolean isLoginExecuted = loginService.doLogin(username, password);
        if (isLoginExecuted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<UserDto> logout() {
        loginService.doLogout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> findByName(@PathVariable String username) {
        UserEntity user = userService.findByUsername(username);
        return ResponseEntity.ok(user.toUserDto());
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateByName(@PathVariable String username, @RequestBody UserDto user) {
        userService.update(username, user.toUserEntity());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserDto> deleteByName(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.status(200).build();
    }
}
