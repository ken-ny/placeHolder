package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
	Sales findTopByOrderByIdDesc();
}
