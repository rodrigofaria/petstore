package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;
    private User loggedUser;

    public boolean doLogin(String username, String password) {
        Optional<User> user = userService.searchByUsernameAndPassword(username, password);
        loggedUser = user.orElseGet(() -> null);
        return user.isPresent();
    }

    public void doLogout() {
        loggedUser = null;
    }

}
