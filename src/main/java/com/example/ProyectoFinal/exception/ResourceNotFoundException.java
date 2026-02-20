package com.example.ProyectoFinal.exception;

/**
 * Excepción personalizada para manejar casos donde un recurso no se encuentra
 * Se lanza cuando se busca un alumno por ID y no existe en la base de datos
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor que acepta un mensaje de error
     * 
     * @param message Mensaje descriptivo del error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje y una causa
     * 
     * @param message Mensaje descriptivo del error
     * @param cause Causa raíz de la excepción
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
