package br.com.rodrigoluisfaria.petstore.service.login;

public interface LoginService {

    void doLogin(String username, String password);

    void doLogout();
}
