package br.com.rodrigoluisfaria.petstore.service.user.exception;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {
        super(message);
    }
}
