package com.ghagos.wsviajersey.model;

public class Error {
	private int code;
	private String message;
	private String description;
	private String link;

	public int getCode() {
		return code;
	}

	public void setCode(int input) {
		this.code = input;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String input) {
		this.message = input;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String input) {
		this.description = input;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String input) {
		this.link = input;
	}
}

