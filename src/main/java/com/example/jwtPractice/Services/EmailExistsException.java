package com.example.jwtPractice.Services;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}
