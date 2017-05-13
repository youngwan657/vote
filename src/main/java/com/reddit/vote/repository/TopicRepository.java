package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TopicRepository {
	Map<Integer, Topic> topicPersistentStorage = new HashMap<>();
	List<Topic> topics = new ArrayList<>();

	public List<Topic> getTopics() {
		return topics;
	}

	public void addTopic(Topic topic) {
		topicPersistentStorage.put(topic.getId(), topic);
		topics.add(topic);
	}

	public void increaseUp(int topicId) {
		Topic topic = topicPersistentStorage.get(topicId);
		topic.increaseUp();
	}

	public void increaseDown(int topicId) {
		Topic topic = topicPersistentStorage.get(topicId);
		topic.increaseDown();
	}
}
