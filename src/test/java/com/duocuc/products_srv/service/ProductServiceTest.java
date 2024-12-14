package com.duocuc.products_srv.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.duocuc.products_srv.model.Category;
import com.duocuc.products_srv.model.Product;
import com.duocuc.products_srv.repository.ProductRepository;

class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetProductsByIds() {
    List<Long> ids = Arrays.asList(1L, 2L);
    List<Product> products = Arrays.asList(
        new Product("Product A", "Description A", 10.0, 5, "url1", Category.FRUTAS),
        new Product("Product B", "Description B", 15.0, 10, "url2", Category.VERDURAS));

    when(productRepository.findByIdIn(ids)).thenReturn(products);

    List<Product> result = productService.getProductsByIds(ids);

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(productRepository, times(1)).findByIdIn(ids);
  }

  @Test
  void testFindAll() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    List<Product> products = Arrays.asList(
        new Product("Product A", "Description A", 10.0, 5, "url1", Category.FRUTAS),
        new Product("Product B", "Description B", 15.0, 10, "url2", Category.VERDURAS));
    Page<Product> productPage = new PageImpl<>(products);

    when(productRepository.findAll(pageRequest)).thenReturn(productPage);

    Page<Product> result = productService.findAll(pageRequest);

    assertNotNull(result);
    assertEquals(2, result.getContent().size());
    verify(productRepository, times(1)).findAll(pageRequest);
  }

  @Test
  void testSearchByKeyword() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    List<Product> products = Arrays.asList(
        new Product("Apple", "Description A", 10.0, 5, "url1", Category.FRUTAS));
    Page<Product> productPage = new PageImpl<>(products);

    when(productRepository.findByNameContainingIgnoreCase("Apple", pageRequest)).thenReturn(productPage);

    Page<Product> result = productService.searchByKeyword("Apple", pageRequest);

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
    verify(productRepository, times(1)).findByNameContainingIgnoreCase("Apple", pageRequest);
  }

  @Test
  void testGetProductById() {
    Product product = new Product("Product A", "Description A", 10.0, 5, "url1", Category.FRUTAS);
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    Optional<Product> result = productService.getProductById(1L);

    assertTrue(result.isPresent());
    assertEquals("Product A", result.get().getName());
    verify(productRepository, times(1)).findById(1L);
  }

  @Test
  void testAddProduct() {
    Product product = new Product("Product A", "Description A", 10.0, 5, "url1", Category.FRUTAS);
    when(productRepository.save(product)).thenReturn(product);

    Product result = productService.addProduct(product);

    assertNotNull(result);
    assertEquals("Product A", result.getName());
    verify(productRepository, times(1)).save(product);
  }

  @Test
  void testUpdateProduct() {
    Product existingProduct = new Product("Product A", "Description A", 10.0, 5, "url1", Category.FRUTAS);
    existingProduct.setId(1L);

    Product updatedDetails = new Product("Product B", "Description B", 15.0, 10, "url2", Category.VERDURAS);

    when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
    when(productRepository.save(existingProduct)).thenReturn(existingProduct);

    Product result = productService.updateProduct(1L, updatedDetails);

    assertNotNull(result);
    assertEquals("Product B", result.getName());
    assertEquals("Description B", result.getDescription());
    verify(productRepository, times(1)).findById(1L);
    verify(productRepository, times(1)).save(existingProduct);
  }

  @Test
  void testUpdateProductNotFound() {
    Product updatedDetails = new Product("Product B", "Description B", 15.0, 10, "url2", Category.VERDURAS);

    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(RuntimeException.class, () -> productService.updateProduct(1L, updatedDetails));

    assertEquals("Product not found with id 1", exception.getMessage());
    verify(productRepository, times(1)).findById(1L);
    verify(productRepository, never()).save(any(Product.class));
  }

  @Test
  void testDeleteProduct() {
    doNothing().when(productRepository).deleteById(1L);

    productService.deleteProduct(1L);

    verify(productRepository, times(1)).deleteById(1L);
  }

  @Test
  void testValidateCategoryThrowsException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> productService.addProduct(new Product("Product A", "Description A", 10.0, 5, "url1", null)));

    assertEquals("Category must not be null", exception.getMessage());
  }
}
