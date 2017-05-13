package com.reddit.vote.domain;

public class Vote {

	private int topicId;
	private VoteType voteType;

	public int getTopicId() {
		return this.topicId;
	}

	public Vote setTopicId(int topicId) {
		this.topicId = topicId;
		return this;
	}

	public VoteType getVoteType() {
		return this.voteType;
	}

	public Vote setVoteType(VoteType voteType) {
		this.voteType = voteType;
		return this;
	}
}
