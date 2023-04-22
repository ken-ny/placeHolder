package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dam.placeholder.entity.Product;

@Service
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findTopByOrderByIdDesc();

	Product findByNameAndRarity(String name, String rarity);
}
