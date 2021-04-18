package br.com.rodrigoluisfaria.petstore.service;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;
    private UserEntity loggedUser;

    public boolean doLogin(String username, String password) {
        loggedUser = userService.searchByUsernameAndPassword(username, password);
        return true;
    }

    public void doLogout() {
        loggedUser = null;
    }
}
