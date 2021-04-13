package br.com.rodrigoluisfaria.petstore.controller;

import br.com.rodrigoluisfaria.petstore.dto.User;
import br.com.rodrigoluisfaria.petstore.service.LoginService;
import br.com.rodrigoluisfaria.petstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
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
        Optional<User> optionalUser = userService.findByUsername(username);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateByName(@PathVariable String username, @RequestBody User user) {
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            userService.update(username, user);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteByName(@PathVariable String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            userService.delete(username);
            return ResponseEntity.ok(optionalUser.get());
        }

        return ResponseEntity.notFound().build();
    }
}
