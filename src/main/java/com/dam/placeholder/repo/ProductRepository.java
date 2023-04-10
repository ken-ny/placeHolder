package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dam.placeholder.entity.Product;
import com.dam.placeholder.entity.ProductKey;

@Service
public interface ProductRepository extends JpaRepository<Product, ProductKey> {

	Product findTopByOrderByProductIdIdDesc();
}
