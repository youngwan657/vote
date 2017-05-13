package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TopicRepository {
	List<Topic> topics = new ArrayList<>();

	public List<Topic> getTopics() {
		return topics;
	}

	public void addTopic(Topic topic) {
		topics.add(topic);
	}
}
