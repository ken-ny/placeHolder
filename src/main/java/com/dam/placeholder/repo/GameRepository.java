package com.dam.placeholder.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dam.placeholder.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

	Game findTopByOrderByIdDesc();

	Game findByNameAndAbbreviation(String name, String abbreviation);

}
