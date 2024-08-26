package com.example.demo.architecture.commons.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleDuplicateUserException() {
        DuplicateUserException ex = new DuplicateUserException("Duplicate user error");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleDuplicateUserException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El correo ya está registrado", response.getBody().getMessage());
        assertEquals("Duplicate user error", response.getBody().getDetails());
    }

    @Test
    void testHandleRuntimeException() {
        RuntimeException ex = new RuntimeException("Runtime error");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleRuntimeException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error en tiempo de ejecución", response.getBody().getMessage());
        assertEquals("Runtime error", response.getBody().getDetails());
    }

    @Test
    void testHandleGenericException() {
        Exception ex = new Exception("Generic error");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGenericException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error interno del servidor", response.getBody().getMessage());
        assertEquals("Generic error", response.getBody().getDetails());
    }

    @Test
    void testHandleValidationErrors() {
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        BindException bindException = new BindException(new Object(), "objectName");
        bindException.addError(fieldError);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleValidationErrors(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error con los parámetros ingresados", response.getBody().getMessage());
        assertEquals("Validación fallida para uno o más campos.", response.getBody().getDetails());
        List<ValidationError> validationErrors = response.getBody().getValidationErrors();
        assertNotNull(validationErrors);
        assertEquals(1, validationErrors.size());
        assertEquals("field", validationErrors.get(0).getField());
        assertEquals("defaultMessage", validationErrors.get(0).getErrorMessage());
    }
}