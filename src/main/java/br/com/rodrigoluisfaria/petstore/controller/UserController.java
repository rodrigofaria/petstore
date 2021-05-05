package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface UserController {

    @PostMapping
    UserDto create(@RequestBody @Valid UserDto user);

    @PostMapping("/createWithArray")
    void createWithArray(@RequestBody List<UserDto> users);

    @PostMapping("/createWithList")
    void createWithList(@RequestBody List<UserDto> users);

    @GetMapping("/login")
    void login(@RequestParam String username, @RequestParam String password);

    @GetMapping("/logout")
    void logout();

    @GetMapping("/{username}")
    UserDto findByName(@PathVariable String username);

    @PutMapping("/{username}")
    UserDto updateByName(@PathVariable String username, @RequestBody UserDto user);

    @DeleteMapping("/{username}")
    void deleteByName(@PathVariable String username);
}
