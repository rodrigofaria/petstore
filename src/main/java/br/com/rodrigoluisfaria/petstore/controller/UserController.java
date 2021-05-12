package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface UserController {

    UserDto create(@RequestBody @Valid UserDto user);

    void createWithArray(@RequestBody List<UserDto> users);

    void createWithList(@RequestBody List<UserDto> users);

    void login(@RequestParam String username, @RequestParam String password);

    void logout();

    UserDto findByName(@PathVariable String username);

    UserDto updateByName(@PathVariable String username, @RequestBody UserDto user);

    void deleteByName(@PathVariable String username);
}
