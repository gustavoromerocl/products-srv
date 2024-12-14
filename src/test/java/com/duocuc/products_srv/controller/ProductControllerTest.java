package com.duocuc.products_srv.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import com.duocuc.products_srv.dto.PageDto;
import com.duocuc.products_srv.dto.ProductIemDto;
import com.duocuc.products_srv.model.Product;
import com.duocuc.products_srv.service.ProductService;

class ProductControllerTest {

  @InjectMocks
  private ProductController productController;

  @Mock
  private ProductService productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetProductsByIds() {
    List<Long> ids = Arrays.asList(1L, 2L);
    Product product1 = new Product("Product 1", "Description 1", 10.0, 5, "url1", null);
    product1.setId(1L);
    Product product2 = new Product("Product 2", "Description 2", 20.0, 10, "url2", null);
    product2.setId(2L);

    when(productService.getProductsByIds(ids)).thenReturn(Arrays.asList(product1, product2));

    ResponseEntity<List<ProductIemDto>> response = productController.getProductsByIds(ids);

    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(2, response.getBody().size());
    assertEquals("Product 1", response.getBody().get(0).getName());

    verify(productService, times(1)).getProductsByIds(ids);
  }

  @Test
  void testGetProductsWithKeyword() {
    String keyword = "test";
    PageRequest pageRequest = PageRequest.of(0, 10);
    Product product = new Product("Test Product", "Description", 15.0, 3, "url", null);
    product.setId(1L);
    Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));

    when(productService.searchByKeyword(keyword, pageRequest)).thenReturn(productPage);

    Object result = productController.getProducts(keyword, 1, 10);

    assertNotNull(result);
    assertTrue(result instanceof PageDto);
    PageDto<Product> pageDto = (PageDto<Product>) result;
    assertEquals(1, pageDto.getTotalElements());

    verify(productService, times(1)).searchByKeyword(keyword, pageRequest);
  }

  @Test
  void testGetProductsWithoutKeyword() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    Product product = new Product("Product", "Description", 10.0, 5, "url", null);
    product.setId(1L);
    Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));

    when(productService.findAll(pageRequest)).thenReturn(productPage);

    Object result = productController.getProducts(null, 1, 10);

    assertNotNull(result);
    assertTrue(result instanceof Page);
    Page<Product> page = (Page<Product>) result;
    assertEquals(1, page.getTotalElements());

    verify(productService, times(1)).findAll(pageRequest);
  }

  @Test
  void testGetProductByIdSuccess() {
    Long productId = 1L;
    Product product = new Product("Product", "Description", 10.0, 5, "url", null);
    product.setId(productId);

    when(productService.getProductById(productId)).thenReturn(Optional.of(product));

    ResponseEntity<Product> response = productController.getProductById(productId);

    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(productId, response.getBody().getId());

    verify(productService, times(1)).getProductById(productId);
  }

  @Test
  void testGetProductByIdNotFound() {
    Long productId = 1L;

    when(productService.getProductById(productId)).thenReturn(Optional.empty());

    ResponseEntity<Product> response = productController.getProductById(productId);

    assertNotNull(response);
    assertEquals(404, response.getStatusCodeValue());

    verify(productService, times(1)).getProductById(productId);
  }

  @Test
  void testAddProduct() {
    Product product = new Product("New Product", "Description", 20.0, 5, "url", null);
    Product savedProduct = new Product("New Product", "Description", 20.0, 5, "url", null);
    savedProduct.setId(1L);

    when(productService.addProduct(product)).thenReturn(savedProduct);

    Product result = productController.addProduct(product);

    assertNotNull(result);
    assertEquals(1L, result.getId());

    verify(productService, times(1)).addProduct(product);
  }

  @Test
  void testUpdateProductSuccess() {
    Long productId = 1L;
    Product updatedProduct = new Product("Updated Product", "Description", 25.0, 8, "url", null);
    updatedProduct.setId(productId);

    when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(updatedProduct);

    ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Updated Product", response.getBody().getName());

    verify(productService, times(1)).updateProduct(eq(productId), any(Product.class));
  }

  @Test
  void testUpdateProductNotFound() {
    Long productId = 1L;
    Product updatedProduct = new Product("Updated Product", "Description", 25.0, 8, "url", null);
    updatedProduct.setId(productId);

    when(productService.updateProduct(eq(productId), any(Product.class))).thenThrow(new RuntimeException("Not found"));

    ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

    assertNotNull(response);
    assertEquals(404, response.getStatusCodeValue());

    verify(productService, times(1)).updateProduct(eq(productId), any(Product.class));
  }

  @Test
  void testDeleteProduct() {
    Long productId = 1L;

    doNothing().when(productService).deleteProduct(productId);

    ResponseEntity<Void> response = productController.deleteProduct(productId);

    assertNotNull(response);
    assertEquals(204, response.getStatusCodeValue());

    verify(productService, times(1)).deleteProduct(productId);
  }
}
