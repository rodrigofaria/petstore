package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface UserController {

    UserDto create(@RequestBody @Valid UserDto user) throws AbstractUserServiceException;

    void createWithArray(@RequestBody List<UserDto> users) throws AbstractUserServiceException;

    void createWithList(@RequestBody List<UserDto> users) throws AbstractUserServiceException;

    void login(@RequestParam String username, @RequestParam String password) throws AbstractUserServiceException;

    void logout();

    UserDto findByName(@PathVariable String username) throws AbstractUserServiceException;

    UserDto updateByName(@PathVariable String username, @RequestBody UserDto user) throws AbstractUserServiceException;

    void deleteByName(@PathVariable String username) throws AbstractUserServiceException;
}
