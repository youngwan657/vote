package com.reddit.vote.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Topic {

	private int id;

	@NotNull
	@Size(min = 1, max = 255, message = "A topic size should be between 1 and 255")
	private String text;

	public int getId() {
		return this.id;
	}

	public Topic id(int id) {
		this.id = id;
		return this;
	}

	public String getText() {
		return this.text;
	}

	public Topic text(String text) {
		this.text = text;
		return this;
	}
}
