package com.projetofinal.backend.exceptions;

public class AlreadyDisabledException extends RuntimeException {
    public AlreadyDisabledException(String message) {
        super(message);
    }
}