package br.com.rodrigoluisfaria.petstore.service.login;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import br.com.rodrigoluisfaria.petstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;
    private UserEntity loggedUser;

    public void doLogin(String username, String password) {
        loggedUser = userService.searchByUsernameAndPassword(username, password);
    }

    public void doLogout() {
        loggedUser = null;
    }
}