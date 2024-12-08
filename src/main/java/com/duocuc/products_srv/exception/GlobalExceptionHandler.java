package com.duocuc.products_srv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  // Manejo de IllegalArgumentException
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // Manejo genérico de excepciones
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Ha ocurrido un error inesperado",
        request.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Clase interna para la estructura de la respuesta de error
  private static class ErrorResponse {
    private int status;
    private String message;
    private String path;

    public ErrorResponse(int status, String message, String path) {
      this.status = status;
      this.message = message;
      this.path = path;
    }

    // Getters y Setters (opcional si usas Lombok)
    public int getStatus() {
      return status;
    }

    public String getMessage() {
      return message;
    }

    public String getPath() {
      return path;
    }
  }
}
