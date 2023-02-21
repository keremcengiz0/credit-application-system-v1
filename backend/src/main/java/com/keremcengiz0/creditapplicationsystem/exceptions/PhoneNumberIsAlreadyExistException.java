package com.keremcengiz0.creditapplicationsystem.exceptions;

public class PhoneNumberIsAlreadyExistException extends RuntimeException{

    public PhoneNumberIsAlreadyExistException(String message) {
        super(message);
    }
}
