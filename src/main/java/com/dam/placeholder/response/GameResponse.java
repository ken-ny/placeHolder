package com.dam.placeholder.response;

import java.util.List;

import com.dam.placeholder.entity.Game;
import com.dam.placeholder.response.utils.ResponseUtils;
import com.dam.placeholder.serializer.ExpansionSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GameResponse {

	@JsonProperty
	private Integer id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String abbreviation;
	@JsonSerialize(using = ExpansionSerializer.class)
	private List<ExpansionResponse> expansions;

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

	public List<ExpansionResponse> getExpansions() {
		return expansions;
	}

	public void setExpansions(List<ExpansionResponse> expansions) {
		this.expansions = expansions;
	}

	public GameResponse(Integer id, String name, String abbreviation, List<ExpansionResponse> expansions) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.expansions = expansions;
	}

	public GameResponse() {
		super();
	}

	public GameResponse(Game game) {
		this.id = game.getId();
		this.abbreviation = game.getAbbreviation();
		this.name = game.getName();

		this.expansions = ResponseUtils.mapperExpansionToResponse(game.getExpansions());
	}

	public GameResponse(Integer id, String name, String abbreviation) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
	}

}
