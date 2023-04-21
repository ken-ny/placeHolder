package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Integer> {

	SaleDetails findTopByOrderByIdDesc();
}
