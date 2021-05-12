package br.com.rodrigoluisfaria.petstore.controller.exception;

import br.com.rodrigoluisfaria.petstore.exception.BadCredentialsException;
import br.com.rodrigoluisfaria.petstore.exception.UserNotFoundException;
import br.com.rodrigoluisfaria.petstore.exception.UsernameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        String error = "Malformed JSON request";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleUsernameAlreadyExistException(
            UsernameAlreadyExistException ex) {
        String error = "Username already exist";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleUserNotFoundException(
            UserNotFoundException ex) {
        String error = "User not found";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleBadCredentialsException(
            BadCredentialsException ex) {
        String error = "username/password are invalid";
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, error, ex);
        return ResponseEntity.status(401).body(apiError);
    }
}
