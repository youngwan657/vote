package com.reddit.vote.domain;

public class Vote {

	private int topicId;
	private UpDown upDown;

	public int getTopicId() {
		return this.topicId;
	}

	public Vote setTopicId(int topicId) {
		this.topicId = topicId;
		return this;
	}

	public UpDown getUpDown() {
		return this.upDown;
	}

	public Vote setUpDown(UpDown upDown) {
		this.upDown = upDown;
		return this;
	}
}
