package com.reddit.vote.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Topic {

	private int id;

	@NotNull
	@Size(min = 1, max = 255, message = "A topic size should be between 1 and 255")
	private String text;

	public Topic() {
	}

	public Topic(String text) {
		this.text = text;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
