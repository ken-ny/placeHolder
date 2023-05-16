package com.dam.placeholder.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.Expansion;

public interface ExpansionRepository extends JpaRepository<Expansion, Integer> {

	Expansion findTopByOrderByIdDesc();

	Optional<Expansion> findByNameAndAbbreviation(String name, String abbreviation);

	Optional<Expansion> findByName(String expansion);

}
