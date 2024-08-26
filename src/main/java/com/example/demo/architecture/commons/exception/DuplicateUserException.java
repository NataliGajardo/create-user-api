package com.example.demo.architecture.commons.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String mensaje) {
        super(mensaje);
    }
}
