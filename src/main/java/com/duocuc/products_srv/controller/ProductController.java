package com.duocuc.products_srv.controller;

import com.duocuc.products_srv.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duocuc.products_srv.model.Product;
import com.duocuc.products_srv.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public Object getProducts(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {

    if (page < 1) {
      throw new IllegalArgumentException("La página no puede ser menor que 1");
    }

    PageRequest pageRequest = PageRequest.of(page - 1, size);

    if (keyword != null && !keyword.isEmpty()) {
      // Manejo de búsqueda por palabra clave
      Page<Product> productsPage = productService.searchByKeyword(keyword, pageRequest);
      return convertPageToDto(productsPage); // Convertir a DTO
    }

    // Manejo estándar
    return productService.findAll(pageRequest);
  }

  // Método para convertir `Page` a `PageDto`
  private PageDto<Product> convertPageToDto(Page<Product> productsPage) {
    return new PageDto<>(
        productsPage.getContent(),
        productsPage.getNumber() + 1,
        productsPage.getSize(),
        productsPage.getTotalElements(),
        productsPage.getTotalPages());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Product addProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
    try {
      return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
