package br.com.rodrigoluisfaria.petstore.service.user.exception;

public class UserNotFoundException extends AbstractUserServiceException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
