package br.com.rodrigoluisfaria.petstore.service;

public interface LoginService {

    boolean doLogin(String username, String password);

    void doLogout();
}
