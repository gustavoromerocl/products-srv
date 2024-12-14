package com.duocuc.products_srv.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class ProductIemDtoTest {

  @Test
  void testConstructorAndGetters() {
    Long id = 1L;
    String name = "Test Product";
    Double price = 19.99;

    ProductIemDto productIemDto = new ProductIemDto(id, name, price);

    assertEquals(id, productIemDto.getId());
    assertEquals(name, productIemDto.getName());
    assertEquals(price, productIemDto.getPrice());
  }

  @Test
  void testSetters() {
    ProductIemDto productIemDto = new ProductIemDto(null, null, null);

    Long id = 2L;
    String name = "Updated Product";
    Double price = 29.99;

    productIemDto.setId(id);
    productIemDto.setName(name);
    productIemDto.setPrice(price);

    assertEquals(id, productIemDto.getId());
    assertEquals(name, productIemDto.getName());
    assertEquals(price, productIemDto.getPrice());
  }

  @Test
  void testEquality() {
    ProductIemDto productIemDto1 = new ProductIemDto(1L, "Product A", 15.99);
    ProductIemDto productIemDto2 = new ProductIemDto(1L, "Product A", 15.99);
    ProductIemDto productIemDto3 = new ProductIemDto(2L, "Product B", 25.99);

    assertEquals(productIemDto1, productIemDto2);
    assertNotEquals(productIemDto1, productIemDto3);
  }

  @Test
  void testHashCode() {
    ProductIemDto productIemDto1 = new ProductIemDto(1L, "Product A", 15.99);
    ProductIemDto productIemDto2 = new ProductIemDto(1L, "Product A", 15.99);

    assertEquals(productIemDto1.hashCode(), productIemDto2.hashCode());
  }

  @Test
  void testToString() {
    ProductIemDto productIemDto = new ProductIemDto(1L, "Product A", 15.99);

    String expected = "ProductIemDto{id=1, name='Product A', price=15.99}";
    assertEquals(expected, productIemDto.toString());
  }
}
