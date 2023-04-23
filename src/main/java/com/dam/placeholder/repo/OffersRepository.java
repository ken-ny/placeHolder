package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Offers;
import com.dam.placeholder.entity.Product;

public interface OffersRepository extends JpaRepository<Offers, Integer> {

	Offers findTopByOrderByIdDesc();

	Offers findByExpansionAndProduct(Expansion expansion, Product product);
}
