package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import com.reddit.vote.domain.Vote;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersistentRepository {
	private Map<Integer, Topic> topics = new HashMap<>();

	public int size() {
		return topics.size();
	}

	public void save(Topic topic) {
		topics.put(topic.getId(), topic);
	}

	public Topic vote(Vote vote) {
		Topic topic = topics.get(vote.getTopicId());
		switch(vote.getVoteType()) {
			case UP:
				topic.upvote();
				break;

			case DOWN:
				topic.downvote();
				break;
		}
		return topic;
	}
}
