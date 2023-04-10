package com.dam.placeholder.request;

import java.util.List;

import com.dam.placeholder.entity.Expansion;

public class GameRequest {

	Integer id;
	String name;
	String abbreviation;
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

	public GameRequest(Integer id, String name, String abbreviation, List<Expansion> expansions) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.expansions = expansions;
	}

	public GameRequest() {
		super();
	}

}
