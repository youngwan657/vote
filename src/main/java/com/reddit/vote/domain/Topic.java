package com.reddit.vote.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {

	private int id;

	@NotNull
	@Size(min = 1, max = 255, message = "A topic size should be between 1 and 255")
	private String text;

	private AtomicInteger up = new AtomicInteger();
	private AtomicInteger down = new AtomicInteger();

	public int getId() {
		return this.id;
	}

	public Topic setId(int id) {
		this.id = id;
		return this;
	}

	public String getText() {
		return this.text;
	}

	public Topic setText(String text) {
		this.text = text;
		return this;
	}

	public int getUp() {
		return this.up.get();
	}

	public Topic upvote() {
		up.incrementAndGet();
		return this;
	}

	public int getDown() {
		return this.down.get();
	}

	public Topic downvote() {
		down.incrementAndGet();
		return this;
	}
}
