package com.dam.placeholder.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dam.placeholder.entity.Card;

@Service
public interface CardRepository extends JpaRepository<Card, Integer> {

	Card findTopByOrderByIdDesc();

	Optional<Card> findByNameAndRarity(String name, String rarity);
}
