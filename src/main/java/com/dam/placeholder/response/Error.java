package com.dam.placeholder.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error {

	private String code;
	private String title;
	private String detail;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Error(String code, String title, String detail) {
		super();
		this.code = code;
		this.title = title;
		this.detail = detail;
	}

	public Error(String code, String title) {
		super();
		this.code = code;
		this.title = title;
	}

	public Error() {
		super();
	}

}
