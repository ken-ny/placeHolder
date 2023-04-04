package com.dam.placeholder.response;

import java.util.Date;

import com.dam.placeholder.entity.Expansion;
import com.dam.placeholder.entity.Game;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpansionResponse {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String abbreviation;

//	@JsonProperty
//	private Date release_date;

	@JsonProperty
	private Boolean is_released;

	@JsonProperty
	private Game game;

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
//
//	public Date getRelease_date() {
//		return release_date;
//	}
//
//	public void setRelease_date(Date release_date) {
//		this.release_date = release_date;
//	}

	public Boolean getIs_released() {
		return is_released;
	}

	public void setIs_released(Boolean is_released) {
		this.is_released = is_released;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ExpansionResponse(Expansion expansion) {

		this.abbreviation = expansion.getAbbreviation();
		this.game = expansion.getGame();
		this.id = expansion.getId();
		this.is_released = expansion.getIs_released();
		this.name = expansion.getName();
//		this.release_date = expansion.getRelease_date();
	}

	public ExpansionResponse(Integer id, String name, String abbreviation, Date release_date, Boolean is_released,
			Game game) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
//		this.release_date = release_date;
		this.is_released = is_released;
		this.game = game;
	}

	public ExpansionResponse() {
		super();
	}

}