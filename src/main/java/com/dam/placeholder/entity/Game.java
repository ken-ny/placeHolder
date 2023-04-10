package com.dam.placeholder.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "GAMES")
public class Game {

	@Id
	Integer id;
	@Column(name = "NAME")
	String name;
	@Column(name = "ABBREVIATION")
	String abbreviation;
	@OneToMany(mappedBy = "game")
	List<Expansion> expansions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public List<Expansion> getExpansions() {
		return expansions;
	}

	public void setExpansions(List<Expansion> expansions) {
		this.expansions = expansions;
	}

	public Game(Integer id, String name, String abbreviation, List<Expansion> expansions) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.expansions = expansions;
	}

	public Game() {
		super();
	}

	public Game(Game game) {

		this.abbreviation = game.getAbbreviation();
		this.expansions = game.getExpansions();
		this.name = game.getName();

	}

}
