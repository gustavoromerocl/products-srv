package com.duocuc.products_srv.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  void testConstructorAndGetters() {
    Product product = new Product("Apple", "Fresh red apple", 1.50, 100, "imageUrl", Category.FRUTAS);

    assertEquals("Apple", product.getName());
    assertEquals("Fresh red apple", product.getDescription());
    assertEquals(1.50, product.getPrice());
    assertEquals(100, product.getStock());
    assertEquals("imageUrl", product.getImageUrl());
    assertEquals(Category.FRUTAS, product.getCategory());
  }

  @Test
  void testSetters() {
    Product product = new Product();
    product.setId(1L);
    product.setName("Milk");
    product.setDescription("Fresh milk");
    product.setPrice(2.0);
    product.setStock(50);
    product.setImageUrl("milkUrl");
    product.setCategory(Category.LACTEOS);

    assertEquals(1L, product.getId());
    assertEquals("Milk", product.getName());
    assertEquals("Fresh milk", product.getDescription());
    assertEquals(2.0, product.getPrice());
    assertEquals(50, product.getStock());
    assertEquals("milkUrl", product.getImageUrl());
    assertEquals(Category.LACTEOS, product.getCategory());
  }

  @Test
  void testDefaultConstructor() {
    Product product = new Product();

    assertNull(product.getId());
    assertNull(product.getName());
    assertNull(product.getDescription());
    assertNull(product.getPrice());
    assertNull(product.getStock());
    assertNull(product.getImageUrl());
    assertNull(product.getCategory());
  }
}
