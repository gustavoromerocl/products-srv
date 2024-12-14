package com.duocuc.products_srv.dto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class PageDtoTest {

  @Test
  void testConstructorAndGetters() {
    // Datos de prueba
    List<String> content = Arrays.asList("Item1", "Item2", "Item3");
    int page = 2;
    int size = 10;
    long totalElements = 30;
    int totalPages = 3;

    // Crear instancia de PageDto
    PageDto<String> pageDto = new PageDto<>(content, page, size, totalElements, totalPages);

    // Verificaciones
    assertEquals(content, pageDto.getContent());
    assertEquals(page, pageDto.getPage());
    assertEquals(size, pageDto.getSize());
    assertEquals(totalElements, pageDto.getTotalElements());
    assertEquals(totalPages, pageDto.getTotalPages());
  }

  @Test
  void testSetters() {
    // Crear instancia vacía
    PageDto<String> pageDto = new PageDto<>(null, 0, 0, 0, 0);

    // Datos de prueba
    List<String> content = Arrays.asList("ItemA", "ItemB");
    int page = 1;
    int size = 5;
    long totalElements = 15;
    int totalPages = 3;

    // Usar setters
    pageDto.setContent(content);
    pageDto.setPage(page);
    pageDto.setSize(size);
    pageDto.setTotalElements(totalElements);
    pageDto.setTotalPages(totalPages);

    // Verificaciones
    assertEquals(content, pageDto.getContent());
    assertEquals(page, pageDto.getPage());
    assertEquals(size, pageDto.getSize());
    assertEquals(totalElements, pageDto.getTotalElements());
    assertEquals(totalPages, pageDto.getTotalPages());
  }

  @Test
  void testDefaultValues() {
    // Crear instancia con valores vacíos
    PageDto<Object> pageDto = new PageDto<>(null, 0, 0, 0, 0);

    // Verificar valores predeterminados
    assertNull(pageDto.getContent());
    assertEquals(0, pageDto.getPage());
    assertEquals(0, pageDto.getSize());
    assertEquals(0, pageDto.getTotalElements());
    assertEquals(0, pageDto.getTotalPages());
  }
}
