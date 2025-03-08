package com.projetofinal.backend.exceptions;

public class UserAlreadyDisabledException extends RuntimeException {
    public UserAlreadyDisabledException(String message) {
        super(message);
    }
}