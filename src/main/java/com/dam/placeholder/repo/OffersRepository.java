package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.Card;
import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Offers;

public interface OffersRepository extends JpaRepository<Offers, Integer> {

	Offers findTopByOrderByIdDesc();

	Offers findByExpansionAndCard(Expansion expansion, Card product);
}
