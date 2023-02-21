package com.keremcengiz0.creditapplicationsystem.exceptions.handler;

import com.keremcengiz0.creditapplicationsystem.entities.ApplicationError;
import com.keremcengiz0.creditapplicationsystem.exceptions.IdentityNumberIsAlreadyExistException;
import com.keremcengiz0.creditapplicationsystem.exceptions.NotFoundException;
import com.keremcengiz0.creditapplicationsystem.exceptions.PhoneNumberIsAlreadyExistException;
import com.keremcengiz0.creditapplicationsystem.exceptions.UserNotFoundException;
import com.keremcengiz0.creditapplicationsystem.repositories.ApplicationErrorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ApplicationErrorRepository applicationErrorRepository;

    @Autowired
    public GlobalExceptionHandler(ApplicationErrorRepository applicationErrorRepository) {
        this.applicationErrorRepository = applicationErrorRepository;
    }

    private ApplicationError initializeError(HttpStatus httpStatus, String message) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setStatus(httpStatus.value());
        applicationError.setMessage(message);
        return applicationError;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ApplicationError> handleNotFoundException(NotFoundException e) {
        ApplicationError applicationError = initializeError(HttpStatus.NOT_FOUND, e.getMessage());
        this.applicationErrorRepository.save(applicationError);
        log.error("GlobalExceptionHandler: " + e.getMessage());
        return new ResponseEntity<>(applicationError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdentityNumberIsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private ResponseEntity<ApplicationError> handleNotFoundException(IdentityNumberIsAlreadyExistException e) {
        ApplicationError applicationError = initializeError(HttpStatus.CONFLICT, e.getMessage());
        this.applicationErrorRepository.save(applicationError);
        log.error("GlobalExceptionHandler: " + e.getMessage());
        return new ResponseEntity<>(applicationError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PhoneNumberIsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private ResponseEntity<ApplicationError> handleNotFoundException(PhoneNumberIsAlreadyExistException e) {
        ApplicationError applicationError = initializeError(HttpStatus.CONFLICT, e.getMessage());
        this.applicationErrorRepository.save(applicationError);
        log.error("GlobalExceptionHandler: " + e.getMessage());
        return new ResponseEntity<>(applicationError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ApplicationError> handleNotFoundException(UserNotFoundException e) {
        ApplicationError applicationError = initializeError(HttpStatus.NOT_FOUND, e.getMessage());
        this.applicationErrorRepository.save(applicationError);
        log.error("GlobalExceptionHandler: " + e.getMessage());
        return new ResponseEntity<>(applicationError, HttpStatus.NOT_FOUND);
    }
}
