package com.dam.placeholder.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.CardMarketRelation;

public interface CardMarketRelationRepository extends JpaRepository<CardMarketRelation, Integer> {

	Optional<CardMarketRelation> findByIdOrder(Integer idOrder);

}
