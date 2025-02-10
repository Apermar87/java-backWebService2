package com.example.webserviceEjercicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta excepción se usa cuando un recurso no existe en la base de datos.

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String mensaje) {
        super(mensaje);
    }
}
