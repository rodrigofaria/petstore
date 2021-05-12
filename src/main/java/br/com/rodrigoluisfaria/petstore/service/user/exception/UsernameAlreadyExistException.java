package br.com.rodrigoluisfaria.petstore.service.user.exception;

public class UsernameAlreadyExistException extends AbstractUserServiceException {

    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
