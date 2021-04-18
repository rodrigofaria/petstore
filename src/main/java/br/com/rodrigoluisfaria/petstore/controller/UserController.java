package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.dto.User;
import br.com.rodrigoluisfaria.petstore.service.LoginService;
import br.com.rodrigoluisfaria.petstore.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/createWithArray")
    public ResponseEntity<User> createWithArray(@RequestBody List<User> users) {
        userService.create(users);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/createWithList")
    public ResponseEntity<User> createWithList(@RequestBody List<User> users) {
        userService.create(users);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "password") String password) {
        boolean isLoginExecuted = loginService.doLogin(username, password);
        if (isLoginExecuted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<User> logout() {
        loginService.doLogout();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByName(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateByName(@PathVariable String username, @RequestBody User user) {
        userService.update(username, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteByName(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.status(200).build();
    }
}
