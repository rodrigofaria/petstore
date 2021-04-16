package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    private User loggedUser;

    public boolean doLogin(String username, String password) {
        Optional<User> user = userService.searchByUsernameAndPassword(username, password);
        loggedUser = user.orElseGet(null);
        return user.isPresent();
    }

    public void doLogout() {
        loggedUser = null;
    }

}
