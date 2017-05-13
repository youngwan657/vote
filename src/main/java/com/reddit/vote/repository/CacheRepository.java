package com.reddit.vote.repository;

import com.reddit.vote.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Repository
public class CacheRepository {
	private PriorityQueue<Topic> topics;
	private List<Topic> top20Topics;

	public CacheRepository() {
		final int DEFAULT_INITIAL_CAPACITY = 11;
		topics = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, (o1, o2) -> (o2.getUp() - o1.getUp()));
		top20Topics = new ArrayList<>();
	}

	public List<Topic> getTop20Topics() {
		return top20Topics;
	}

	public void save(Topic topic) {
		topics.add(topic);
		refreshTop20Topics();
	}

	public void refresh(Topic topic) {
		topics.remove(topic);
		topics.add(topic);
		refreshTop20Topics();
	}

	private void refreshTop20Topics() {
		final int TOP20_TOPICS = 20;

		top20Topics.clear();
		for (int i = 0; i < TOP20_TOPICS && 0 < topics.size(); i++) {
			top20Topics.add(topics.poll());
		}

		topics.addAll(top20Topics);
	}
}
