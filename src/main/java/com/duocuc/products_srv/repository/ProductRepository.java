package com.duocuc.products_srv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duocuc.products_srv.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // Búsqueda por nombre
  Page<Product> findByNameContainingIgnoreCase(String nameKeyword, Pageable pageable);

  // Búsqueda por descripción
  Page<Product> findByDescriptionContainingIgnoreCase(String descKeyword, Pageable pageable);

  // Búsqueda por nombre o descripción
  Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameKeyword, String descKeyword,
      Pageable pageable);

  List<Product> findByIdIn(List<Long> ids); // Buscar productos por una lista de IDs
}
