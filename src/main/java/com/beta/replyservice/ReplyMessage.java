package com.beta.replyservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplyMessage {

	@JsonProperty("data")
	private final String message;

	public ReplyMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}