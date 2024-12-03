package com.duocuc.products_srv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duocuc.products_srv.model.Category;
import com.duocuc.products_srv.model.Product;
import com.duocuc.products_srv.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product addProduct(Product product) {
    validateCategory(product.getCategory());
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product productDetails) {
    validateCategory(productDetails.getCategory());
    return productRepository.findById(id)
        .map(product -> {
          product.setName(productDetails.getName());
          product.setDescription(productDetails.getDescription());
          product.setPrice(productDetails.getPrice());
          product.setStock(productDetails.getStock());
          product.setImageUrl(productDetails.getImageUrl());
          product.setCategory(productDetails.getCategory());
          return productRepository.save(product);
        })
        .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  private void validateCategory(Category category) {
    if (category == null) {
      throw new IllegalArgumentException("Category must not be null");
    }
  }
}
