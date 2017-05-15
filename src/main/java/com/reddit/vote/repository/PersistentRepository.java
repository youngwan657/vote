package com.reddit.vote.repository;

import com.google.common.base.Preconditions;
import com.reddit.vote.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PersistentRepository {
	private Map<Integer, Topic> topics = new ConcurrentHashMap<>();

	public void save(Topic topic) {
		Preconditions.checkNotNull(topic);
		topics.put(topic.getId(), topic);
	}

	public Topic upvote(Integer topicId) {
		Preconditions.checkNotNull(topics.get(topicId));
		return topics.get(topicId).upvote();
	}

	public Topic downvote(Integer topicId) {
		Preconditions.checkNotNull(topics.get(topicId));
		return topics.get(topicId).downvote();
	}

	public Map<Integer, Topic> getTopics() {
		return topics;
	}
}
