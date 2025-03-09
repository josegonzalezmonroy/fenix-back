package com.projetofinal.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        String mensagemErro = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos. Verifique e tente novamente.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
    }
}