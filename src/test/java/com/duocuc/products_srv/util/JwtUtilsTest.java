package com.duocuc.products_srv.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

class JwtUtilsTest {

  private JwtUtils jwtUtils;

  private final String secret = Base64.getEncoder().encodeToString(
      "NkxhR2NFdDJ5VGxzNkd3aG9MYmVkdG1qSDFucEJIZnBxSGQ0SkxCVHVxZnN3b1RlMjhiM2dQQzRScmxzOFZUUg==".getBytes());

  @BeforeEach
  void setUp() {
    jwtUtils = new JwtUtils();
    // Inyectar el valor del secreto directamente
    String encodedSecret = Base64.getEncoder().encodeToString(secret.getBytes());
    ReflectionTestUtils.setField(jwtUtils, "secret", encodedSecret);
  }

  @Test
  void testValidateToken_ValidToken() {
    String token = generateToken("testuser");

    boolean isValid = jwtUtils.validateToken(token);

    assertTrue(isValid, "El token debería ser válido.");
  }

  @Test
  void testValidateToken_InvalidToken() {
    String invalidToken = "invalid.token";

    boolean isValid = jwtUtils.validateToken(invalidToken);

    assertFalse(isValid, "El token debería ser inválido.");
  }

  @Test
  void testGetClaimsFromToken_ValidToken() {
    String username = "testuser";
    String token = generateToken(username);

    Claims claims = jwtUtils.getClaimsFromToken(token);

    assertNotNull(claims, "Claims no debería ser null.");
    assertEquals(username, claims.getSubject(), "El subject debería coincidir con el username.");
  }

  @Test
  void testGetClaimsFromToken_InvalidToken() {
    String invalidToken = "invalid.token";

    assertThrows(Exception.class, () -> jwtUtils.getClaimsFromToken(invalidToken),
        "Debería lanzar una excepción para un token inválido.");
  }

  // Método auxiliar para generar un token válido
  private String generateToken(String username) {
    Key signingKey = Keys.hmacShaKeyFor(secret.getBytes());

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora de expiración
        .signWith(signingKey)
        .compact();
  }
}
