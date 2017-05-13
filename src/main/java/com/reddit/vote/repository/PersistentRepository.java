package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
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

	public Topic upvote(int topicId) {
		return topics.get(topicId).upvote();
	}

	public Topic downvote(int topicId) {
		return topics.get(topicId).downvote();
	}
}
