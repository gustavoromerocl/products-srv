package com.duocuc.products_srv.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  void testCategoryValues() {
    // Obtener todas las categorías
    Category[] categories = Category.values();

    // Verificar que no sean nulas y contengan los valores esperados
    assertNotNull(categories);
    assertEquals(6, categories.length); // Hay 6 categorías en el enum
    assertTrue(containsCategory(categories, "FRUTAS"));
    assertTrue(containsCategory(categories, "VERDURAS"));
    assertTrue(containsCategory(categories, "LACTEOS"));
    assertTrue(containsCategory(categories, "CARNES"));
    assertTrue(containsCategory(categories, "BEBIDAS"));
    assertTrue(containsCategory(categories, "SNACKS"));
  }

  @Test
  void testCategoryValueOf() {
    // Probar el método valueOf para cada categoría
    assertEquals(Category.FRUTAS, Category.valueOf("FRUTAS"));
    assertEquals(Category.VERDURAS, Category.valueOf("VERDURAS"));
    assertEquals(Category.LACTEOS, Category.valueOf("LACTEOS"));
    assertEquals(Category.CARNES, Category.valueOf("CARNES"));
    assertEquals(Category.BEBIDAS, Category.valueOf("BEBIDAS"));
    assertEquals(Category.SNACKS, Category.valueOf("SNACKS"));
  }

  private boolean containsCategory(Category[] categories, String categoryName) {
    for (Category category : categories) {
      if (category.name().equals(categoryName)) {
        return true;
      }
    }
    return false;
  }
}
