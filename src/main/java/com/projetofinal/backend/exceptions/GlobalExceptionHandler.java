package com.projetofinal.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projetofinal.backend.controller.ADMIN.ResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationExceptions(MethodArgumentNotValidException e) {
        String mensagemErro = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos. Verifique e tente novamente.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(mensagemErro));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String mensagemErro = "Formato de dados inválido. Verifique os campos e tente novamente.";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(mensagemErro));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ResponseMessage> handleInvalidDateException(InvalidDateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
    }
}