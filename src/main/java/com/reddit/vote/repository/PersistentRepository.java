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

	public void handleUpDown(Vote vote) {
		switch(vote.getUpDown()) {
			case UP:
				topics.get(vote.getTopicId()).increaseUp();
				break;

			case DOWN:
				topics.get(vote.getTopicId()).increaseDown();
				break;
		}
	}
}
