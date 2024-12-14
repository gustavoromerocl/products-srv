package com.duocuc.products_srv.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

  @Test
  void handleIllegalArgumentException_ShouldReturnBadRequest() {
    // Arrange
    IllegalArgumentException exception = new IllegalArgumentException("Invalid input");
    MockHttpServletRequest mockRequest = new MockHttpServletRequest();
    mockRequest.setRequestURI("/test-endpoint");
    WebRequest webRequest = new ServletWebRequest(mockRequest);

    // Act
    ResponseEntity<Object> response = exceptionHandler.handleIllegalArgumentException(exception, webRequest);

    // Assert
    assertNotNull(response, "La respuesta no debe ser null.");
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "El código de estado debe ser 400.");
    GlobalExceptionHandler.ErrorResponse errorResponse = (GlobalExceptionHandler.ErrorResponse) response.getBody();
    assertNotNull(errorResponse, "El cuerpo de la respuesta no debe ser null.");
    assertEquals(400, errorResponse.getStatus(), "El código de estado debe coincidir.");
    assertEquals("Invalid input", errorResponse.getMessage(), "El mensaje de error debe coincidir.");
    assertEquals("uri=/test-endpoint", errorResponse.getPath(), "La ruta debe coincidir.");
  }

  @Test
  void handleGeneralException_ShouldReturnInternalServerError() {
    // Arrange
    Exception exception = new Exception("Unexpected error");
    MockHttpServletRequest mockRequest = new MockHttpServletRequest();
    mockRequest.setRequestURI("/test-endpoint");
    WebRequest webRequest = new ServletWebRequest(mockRequest);

    // Act
    ResponseEntity<Object> response = exceptionHandler.handleGeneralException(exception, webRequest);

    // Assert
    assertNotNull(response, "La respuesta no debe ser null.");
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "El código de estado debe ser 500.");
    GlobalExceptionHandler.ErrorResponse errorResponse = (GlobalExceptionHandler.ErrorResponse) response.getBody();
    assertNotNull(errorResponse, "El cuerpo de la respuesta no debe ser null.");
    assertEquals(500, errorResponse.getStatus(), "El código de estado debe coincidir.");
    assertEquals("Ha ocurrido un error inesperado", errorResponse.getMessage(), "El mensaje de error debe coincidir.");
    assertEquals("uri=/test-endpoint", errorResponse.getPath(), "La ruta debe coincidir.");
  }
}
