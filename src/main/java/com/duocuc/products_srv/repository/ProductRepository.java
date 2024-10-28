package com.duocuc.products_srv.repository;

import com.duocuc.products_srv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
