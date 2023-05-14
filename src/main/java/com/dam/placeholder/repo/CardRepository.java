package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dam.placeholder.entity.Card;

@Service
public interface CardRepository extends JpaRepository<Card, Integer> {

	Card findTopByOrderByIdDesc();

	Card findByNameAndRarity(String name, String rarity);
}
