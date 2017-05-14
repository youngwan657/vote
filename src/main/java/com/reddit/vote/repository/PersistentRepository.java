package com.reddit.vote.repository;

import com.google.common.base.Preconditions;
import com.reddit.vote.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersistentRepository {
	private Map<Integer, Topic> topics = new HashMap<>();

	public void save(Topic topic) {
		Preconditions.checkNotNull(topic);
		topics.put(topic.getId(), topic);
	}

	public Topic upvote(int topicId) {
		Preconditions.checkNotNull(topics.get(topicId));
		return topics.get(topicId).upvote();
	}

	public Topic downvote(int topicId) {
		Preconditions.checkNotNull(topics.get(topicId));
		return topics.get(topicId).downvote();
	}

	public Map<Integer, Topic> getTopics() {
		return topics;
	}
}
