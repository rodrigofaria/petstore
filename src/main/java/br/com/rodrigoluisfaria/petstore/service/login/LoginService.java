package br.com.rodrigoluisfaria.petstore.service.login;

import br.com.rodrigoluisfaria.petstore.service.user.exception.AbstractUserServiceException;

public interface LoginService {

    void doLogin(String username, String password) throws AbstractUserServiceException;

    void doLogout();
}
