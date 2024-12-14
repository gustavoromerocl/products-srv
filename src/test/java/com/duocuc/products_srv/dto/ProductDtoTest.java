package com.duocuc.products_srv.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.duocuc.products_srv.model.Category;

class ProductDtoTest {

  @Test
  void testConstructorAndGetters() {
    Long id = 1L;
    String name = "Test Product";
    String description = "Test Description";
    Double price = 10.0;
    Integer stock = 5;
    String imageUrl = "http://example.com/image.jpg";
    Category category = Category.FRUTAS;

    ProductDto productDto = new ProductDto(id, name, description, price, stock, imageUrl, category);

    assertEquals(id, productDto.getId());
    assertEquals(name, productDto.getName());
    assertEquals(description, productDto.getDescription());
    assertEquals(price, productDto.getPrice());
    assertEquals(stock, productDto.getStock());
    assertEquals(imageUrl, productDto.getImageUrl());
    assertEquals(category, productDto.getCategory());
  }

  @Test
  void testSetters() {
    ProductDto productDto = new ProductDto();

    Long id = 1L;
    String name = "Test Product";
    String description = "Test Description";
    Double price = 10.0;
    Integer stock = 5;
    String imageUrl = "http://example.com/image.jpg";
    Category category = Category.VERDURAS;

    productDto.setId(id);
    productDto.setName(name);
    productDto.setDescription(description);
    productDto.setPrice(price);
    productDto.setStock(stock);
    productDto.setImageUrl(imageUrl);
    productDto.setCategory(category);

    assertEquals(id, productDto.getId());
    assertEquals(name, productDto.getName());
    assertEquals(description, productDto.getDescription());
    assertEquals(price, productDto.getPrice());
    assertEquals(stock, productDto.getStock());
    assertEquals(imageUrl, productDto.getImageUrl());
    assertEquals(category, productDto.getCategory());
  }

  @Test
  void testEquality() {
    ProductDto productDto1 = new ProductDto(1L, "Product A", "Description A", 20.0, 10, "url1", Category.FRUTAS);
    ProductDto productDto2 = new ProductDto(1L, "Product A", "Description A", 20.0, 10, "url1", Category.FRUTAS);

    assertEquals(productDto1, productDto2);
  }

  @Test
  void testHashCode() {
    ProductDto productDto1 = new ProductDto(1L, "Product A", "Description A", 20.0, 10, "url1", Category.SNACKS);
    ProductDto productDto2 = new ProductDto(1L, "Product A", "Description A", 20.0, 10, "url1", Category.SNACKS);

    assertEquals(productDto1.hashCode(), productDto2.hashCode());
  }

  @Test
  void testToString() {
    ProductDto productDto = new ProductDto(1L, "Product B", "Description B", 25.0, 15, "url2", Category.CARNES);

    String expected = "ProductDto{id=1, name='Product B', description='Description B', price=25.0, stock=15, category=CARNES, imageUrl='url2'}";
    assertEquals(expected, productDto.toString());
  }
}
