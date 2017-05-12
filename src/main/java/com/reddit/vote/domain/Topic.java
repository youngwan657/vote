package com.reddit.vote.domain;

import javax.validation.constraints.Size;

public class Topic {

	@Size(min = 1, max = 255, message = "A topic size should be between 1 and 255")
	private String text;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
