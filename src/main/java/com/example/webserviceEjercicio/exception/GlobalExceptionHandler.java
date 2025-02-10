package com.example.webserviceEjercicio.exception;

// Clase global que maneja todas las excepciones en el sistema.

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException excepcion) {
        return buildResponse(HttpStatus.NOT_FOUND, "Not found", excepcion.getMessage());
    }

    // Excepción para peticiones inválidas
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException excepcion) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Bad request", excepcion.getMessage());
    }


    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus estado, String error, String mensaje) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", estado.value());
        response.put("error", error);
        response.put("message", mensaje);
        return new ResponseEntity<>(response, estado);
    }
}
