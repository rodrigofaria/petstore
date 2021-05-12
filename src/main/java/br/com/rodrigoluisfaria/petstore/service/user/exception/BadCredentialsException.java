package br.com.rodrigoluisfaria.petstore.service.user.exception;

public class BadCredentialsException extends AbstractUserServiceException {

    public BadCredentialsException(String message) {
        super(message);
    }
}
