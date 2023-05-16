package com.dam.placeholder.utils;

public enum ErrorCodes {

	NOT_FOUND_RESULT("001", "NO RESULT FOUND"), GENERIC_ERROR("999", "AN ERROR HAS HAPPENED, CHECK THE DETAILS");

	private final String code;
	private final String title;

	ErrorCodes(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

}
