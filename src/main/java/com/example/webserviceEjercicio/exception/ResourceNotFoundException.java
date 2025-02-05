package com.example.webserviceEjercicio.exception;

// Esta excepción se usa cuando un recurso no existe en la base de datos.

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String message) {
        super(message);
    }
}
