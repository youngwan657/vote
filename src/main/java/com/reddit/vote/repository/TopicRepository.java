package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import com.reddit.vote.domain.Vote;
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

	public void handleUpDown(Vote vote) {
		switch(vote.getUpDown()) {
			case UP:
				topicPersistentStorage.get(vote.getTopicId()).increaseUp();
				break;

			case DOWN:
				topicPersistentStorage.get(vote.getTopicId()).increaseDown();
				break;
		}
	}
}
