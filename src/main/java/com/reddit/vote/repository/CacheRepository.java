package com.reddit.vote.repository;

import com.reddit.vote.domain.Topic;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Repository
public class CacheRepository {
	private PriorityQueue<Topic> topics;
	private List<Topic> popularTopics;

	public CacheRepository() {
		final int DEFAULT_INITIAL_CAPACITY = 11;
		topics = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, (o1, o2) -> (o2.getUp() - o1.getUp()));
		popularTopics = new ArrayList<>();
	}

	public List<Topic> getTopics() {
		return popularTopics;
	}

	public void save(Topic topic) {
		topics.add(topic);
		refreshPopularTopics();
	}

	public void refresh(Topic topic) {
		topics.remove(topic);
		topics.add(topic);
		refreshPopularTopics();
	}

	private void refreshPopularTopics() {
		final int LIMIT = 20;

		popularTopics.clear();
		for (int i = 0; i < LIMIT && 0 < topics.size(); i++) {
			popularTopics.add(topics.poll());
		}

		topics.addAll(popularTopics);
	}
}
