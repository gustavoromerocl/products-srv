package com.duocuc.products_srv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duocuc.products_srv.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // Buscar productos por nombre (o descripción) que contengan una palabra clave,
  // ignorando mayúsculas/minúsculas
  Page<Product> findByNameContainingIgnoreCase(String nameKeyword, String descKeyword,
      Pageable pageable);
}
